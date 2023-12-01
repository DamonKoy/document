#!/bin/bash
source /etc/profile

# 获取上游项目的执行环境，并设置接口测试对应的执行环境
echo "hashversion=${hashversion}"
echo "envir=${envir}"
echo "debug_info=${debug_info}"
echo "describe=${describe}"
envir_tmp=${envir#*/}
envir=${envir_tmp%.*}

if [ $envir == 'demo-u-api' ];then
  test_env='demo'
    exec_file='testsuites/P0_demo_testsuite.yml'
    config_file='env/demo.env'
elif [ $envir == 'api2-u-api' ];then
    test_env='api2'
    exec_file='testsuites/P0_api2_testsuite.yml'
    config_file='env/api2.env'
elif [ $envir == 'demo-cf-api' ];then
    test_env='demo-cf'
    exec_file='testsuites/P0_demo_cf_testsuite.yml'
    config_file='env/demo_cf.env'
elif [ $envir == 'api2-cf-api' ];then
    test_env='api2-cf'
    exec_file='testsuites/P0_api2_cf_testsuite.yml'
    config_file='env/api2_cf.env'
else
    test_env='none'
fi

# 判断是否需要执行测试
if [ $test_env == 'none' ];then
  echo "构建环境是$envir，不需要执行接口测试"
else
  # 旧项目变量
  project_name=chuman-api-test
  report_html=reports/$BUILD_NUMBER.html
  exec_file_tmp=${exec_file#*/}
  report_json="logs/testsuites/${exec_file_tmp%.*}.summary.json"
  log_url=$BUILD_URL/console
  show_report_url="http://qa-report-dev.chumanapp.com/"

  # 新项目变量
  new_project_name=chuman-api-test-new
  new_report_html=allure-report/$BUILD_NUMBER/index.html
  new_report_dir=allure-report/$BUILD_NUMBER  
  file_name='testcases'
  prometheusData="allure-report/$BUILD_NUMBER/export/prometheusData.txt"
  behaviors_json="allure-report/$BUILD_NUMBER/data/test-cases/behaviors.json"
  status_json="allure-report/$BUILD_NUMBER/widgets/status-chart.json"
  
  # 公共变量
  test_time=$(date "+%Y-%m-%d %H:%M:%S")
  ding_url='https://oapi.dingtalk.com/robot/send?access_token=83f04c75d4ec2a260b8bf7e959c98374cfaebd34b4973dac03a838d20c217287'

  # 执行旧项目脚本
  cd chuman-api-test
  pipenv install --skip-lock
  pipenv run hrun $exec_file --dot-env-path $config_file --log-level debug --save-tests --report-file $report_html

  # 执行旧项目报告生产数据
  if [ -f $report_html ];then
    show_report_dir="/data/qa-report/"
    show_report_name="${JOB_NAME}报告_$BUILD_NUMBER.html"

    # 把生成的报告移动到展示的目录下
    cp $report_html ${show_report_dir}${show_report_name}

    report_url=$show_report_url$show_report_name
        echo $report_url
    case_total=$(cat $report_json | jq '.stat.testcases.total')
    case_success=$(cat $report_json | jq '.stat.testcases.success')
    case_fail=$(cat $report_json | jq '.stat.testcases.fail')
    fail_module=''
      if [ $case_fail != '0' ];then
        for index in `seq 0 $case_total`
        do
          success=$(cat $report_json | jq -r ".details[$index].success")
          if [ $success == 'false' ];then
            name=$(cat $report_json | jq -r ".details[$index].name")
            fail_module=$fail_module$'\n'$name
          fi
        done
      else
        fail_module='无'
    fi
  fi  
  # 执行新项目脚本
  cd ..
  cd chuman-api-test-new
  pipenv install --skip-lock
  pipenv run pytest $file_name --env=$test_env 
  allure generate reports -o allure-report/$BUILD_NUMBER --clean

  # 执行新项目报告生产数据
  if [ -f $new_report_html ];then
    new_show_report_dir="/data/qa-report/"
    new_show_report_name="${JOB_NAME}报告_allure_report_$BUILD_NUMBER"

    # 把生成的报告移动到展示的目录下
    cp -r $new_report_dir ${new_show_report_dir}${new_show_report_name}
    new_report_url=$show_report_url$new_show_report_name
    echo $new_report_url

    run_num=`grep "launch_retries_run" $prometheusData|awk '{print $NF}'`
    passed_num=`grep "launch_status_passed" $prometheusData|awk '{print $NF}'`
    failed_num=`grep "launch_status_failed" $prometheusData|awk '{print $NF}'`
    broken_num=`grep "launch_status_broken" $prometheusData|awk '{print $NF}'`
    skipped_num=`grep "launch_status_skipped" $prometheusData|awk '{print $NF}'`

    broken_module=''
      if [ $broken_num != '0' ];then
        for index in `seq 0 $run_num`
        do
          result=$(cat $status_json | jq ".[$index].status")
          echo $result
          if [ $result == 'broken' ];then
            broken_name=$(cat $status_json | jq ".[$index].name")
            echo $broken_name
            broken_module=$broken_module$'\n'$broken_name
            echo $broken_module
          fi
        done
      else
        broken_module='无'
    fi
    # 统计所有项目执行数据
    run_sum=$(($case_total+$run_num-$skipped_num))
    success_sum=$(($case_success+$passed_num))
    failed_sum=$(($failed_num+$broken_num+$case_fail))
    if [[ $fail_module == '无' ]] && [[ $broken_module == '无' ]];then
    	failed_module='无'
    elif [ $fail_module == '无' ];then
    	failed_module=$broken_module
    elif [ $broken_module == '无' ];then
    	failed_module=$fail_module
    else 
    	failed_module=$fail_module$'\n'$broken_module
    fi
  fi
	ding_message="后端${test_env}环境代码发布\n发布用户：${BUILD_USER}\n代码hash：${hashversion}\ndebug_info：${debug_info}\n发布描述：${describe}\n\n触漫接口自动化测试执行完毕，以下为执行结果：\n执行环境：${test_env}\n执行时间：${test_time}\n测试场景总数：${run_sum}\n成功场景数：${success_sum}\n失败场景数：${failed_sum}\n\n失败涉及模块：${failed_module}\n触漫P0接口测试报告：$report_url\n触漫P1~P2接口报告：$new_report_url"

	curl $ding_url \
   		-H 'Content-Type: application/json' \
   		-d  '{"msgtype": "text",
       			"text": {
       		    		"content": "'"${ding_message}"'"
       			}
      		}'
fi


