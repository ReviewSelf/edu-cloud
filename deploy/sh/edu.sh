#!/bin/sh
export SYSTEM=edu-cloud-system.jar
export FILE=edu-cloud-file.jar
export JUDGE=edu-cloud-judge.jar
export PROBLEM=edu-cloud-problem.jar
export TEACHING=edu-cloud-teaching.jar
export AFFAIR=edu-cloud-affair.jar
export LESSONS=edu-cloud-lessons.jar
export WECHAT=edu-cloud-wechat.jar
export GATEWAY=edu-cloud-gateway.jar


export SYSTEM_port=7000
export FILE_port=7002
export JUDGE_port=7004
export PROBLEM_port=7001
export TEACHING_port=7006
export AFFAIR_port=7003
export LESSONS_port=7005
export WECHAT_port=7007
export GATEWAY_port=8080

case "$1" in

start)
        ## 启动SYSTEM
        echo "--------SYSTEM 开始启动--------------"
        nohup java -jar $SYSTEM >/dev/null 2>&1 &
        SYSTEM_pid=`lsof -i:$SYSTEM_port|grep "LISTEN"|awk '{print $2}'`
        until [ -n "$SYSTEM_pid" ]
            do
              SYSTEM_pid=`lsof -i:$SYSTEM_port|grep "LISTEN"|awk '{print $2}'`
            done
        echo "SYSTEM pid is $SYSTEM_pid"
        echo "--------SYSTEM 启动成功--------------"

        ## 启动FILE
        echo "--------开始启动FILE---------------"
        nohup java -jar $FILE >/dev/null 2>&1 &
        FILE_pid=`lsof -i:$FILE_port|grep "LISTEN"|awk '{print $2}'`
        until [ -n "$FILE_pid" ]
            do
              FILE_pid=`lsof -i:$FILE_port|grep "LISTEN"|awk '{print $2}'`
            done
        echo "FILE pid is $FILE_pid"
        echo "---------FILE 启动成功-----------"

		## 启动JUDGE
        echo "--------开始启动JUDGE---------------"
        nohup java -jar $JUDGE >/dev/null 2>&1 &
        JUDGE_pid=`lsof -i:$JUDGE_port|grep "LISTEN"|awk '{print $2}'`
        until [ -n "$JUDGE_pid" ]
            do
              JUDGE_pid=`lsof -i:$JUDGE_port|grep "LISTEN"|awk '{print $2}'`
            done
        echo "JUDGE pid is $JUDGE_pid"
        echo "---------JUDGE 启动成功-----------"

		## 启动PROBLEM
        echo "--------开始启动PROBLEM---------------"
        nohup java -jar $PROBLEM >/dev/null 2>&1 &
        PROBLEM_pid=`lsof -i:$PROBLEM_port|grep "LISTEN"|awk '{print $2}'`
        until [ -n "$PROBLEM_pid" ]
            do
              PROBLEM_pid=`lsof -i:$PROBLEM_port|grep "LISTEN"|awk '{print $2}'`
            done
        echo "PROBLEM pid is $PROBLEM_pid"
        echo "---------PROBLEM 启动成功-----------"

		## 启动TEACHING
        echo "--------开始启动TEACHING---------------"
        nohup java -jar $TEACHING >/dev/null 2>&1 &
        TEACHING_pid=`lsof -i:$TEACHING_port|grep "LISTEN"|awk '{print $2}'`
        until [ -n "$TEACHING_pid" ]
            do
              TEACHING_pid=`lsof -i:$TEACHING_port|grep "LISTEN"|awk '{print $2}'`
            done
        echo "TEACHING pid is $TEACHING_pid"
        echo "---------TEACHING 启动成功-----------"

		## 启动AFFAIR
        echo "--------开始启动AFFAIR---------------"
        nohup java -jar $AFFAIR >/dev/null 2>&1 &
        AFFAIR_pid=`lsof -i:$AFFAIR_port|grep "LISTEN"|awk '{print $2}'`
        until [ -n "$AFFAIR_pid" ]
            do
              AFFAIR_pid=`lsof -i:$AFFAIR_port|grep "LISTEN"|awk '{print $2}'`
            done
        echo "AFFAIR pid is $AFFAIR_pid"
        echo "---------AFFAIR 启动成功-----------"

		## 启动LESSONS
        echo "--------开始启动LESSONS---------------"
        nohup java -jar $LESSONS >/dev/null 2>&1 &
        LESSONS_pid=`lsof -i:$LESSONS_port|grep "LISTEN"|awk '{print $2}'`
        until [ -n "$LESSONS_pid" ]
            do
              LESSONS_pid=`lsof -i:$LESSONS_port|grep "LISTEN"|awk '{print $2}'`
            done
        echo "LESSONS pid is $LESSONS_pid"
        echo "---------LESSONS 启动成功-----------"

		## 启动WECHAT
        echo "--------开始启动WECHAT---------------"
        nohup java -jar $WECHAT >/dev/null 2>&1 &
        WECHAT_pid=`lsof -i:$WECHAT_port|grep "LISTEN"|awk '{print $2}'`
        until [ -n "$WECHAT_pid" ]
            do
              WECHAT_pid=`lsof -i:$WECHAT_port|grep "LISTEN"|awk '{print $2}'`
            done
        echo "WECHAT pid is $WECHAT_pid"
        echo "---------WECHAT 启动成功-----------"

		## 启动GATEWAY
        echo "--------开始启动GATEWAY---------------"
        nohup java -jar $GATEWAY >/dev/null 2>&1 &
        GATEWAY_pid=`lsof -i:$GATEWAY_port|grep "LISTEN"|awk '{print $2}'`
        until [ -n "$GATEWAY_pid" ]
            do
              GATEWAY_pid=`lsof -i:$GATEWAY_port|grep "LISTEN"|awk '{print $2}'`
            done
        echo "GATEWAY pid is $GATEWAY_pid"
        echo "---------GATEWAY 启动成功-----------"

        echo "===startAll success==="
        ;;

 stop)
        P_ID=`ps -ef | grep -w $SYSTEM | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "===SYSTEM process not exists or stop success"
        else
            kill -9 $P_ID
            echo "SYSTEM killed success"
        fi
		    P_ID=`ps -ef | grep -w $FILE | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "===FILE process not exists or stop success"
        else
            kill -9 $P_ID
            echo "FILE killed success"
        fi
		    P_ID=`ps -ef | grep -w $JUDGE | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "===JUDGE process not exists or stop success"
        else
            kill -9 $P_ID
            echo "JUDGE killed success"
        fi
		    P_ID=`ps -ef | grep -w $PROBLEM | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "===PROBLEM process not exists or stop success"
        else
            kill -9 $P_ID
            echo "PROBLEM killed success"
        fi
		    P_ID=`ps -ef | grep -w $TEACHING | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "===TEACHING process not exists or stop success"
        else
            kill -9 $P_ID
            echo "TEACHING killed success"
        fi
		    P_ID=`ps -ef | grep -w $AFFAIR | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "===AFFAIR process not exists or stop success"
        else
            kill -9 $P_ID
            echo "AFFAIR killed success"
        fi
		    P_ID=`ps -ef | grep -w $LESSONS | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "===LESSONS process not exists or stop success"
        else
            kill -9 $P_ID
            echo "LESSONS killed success"
        fi
		    P_ID=`ps -ef | grep -w $WECHAT | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "===WECHAT process not exists or stop success"
        else
            kill -9 $P_ID
            echo "WECHAT killed success"
        fi
		    P_ID=`ps -ef | grep -w $GATEWAY | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "===GATEWAY process not exists or stop success"
        else
            kill -9 $P_ID
            echo "GATEWAY killed success"
        fi
        echo "===stopAll success==="
        ;;

restart)
        $0 stop
        sleep 2
        $0 start
        echo "===restartAll success==="
        ;;
*)
        echo "请输入 start || stop ||restart"
esac
exit 0
