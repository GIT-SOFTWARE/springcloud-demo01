/*******************************************************************************
 * @jsname:billScan_controller.js
 * @author:WangDongping
 * @date:2017-08-14
 * @use:票据扫码.js
 ******************************************************************************/
var ngApp = angular.module('ngApp', []).config(
    [ '$interpolateProvider', function($interpolateProvider) {
        $interpolateProvider.startSymbol('[[');
        $interpolateProvider.endSymbol(']]');
    } ]);
var storage = window.localStorage;
ngApp.controller('billScanController',['$scope','$http','$timeout','$sce',function($scope,$http,$timeout,$sce){

    //页面一开始展示
    $scope.isFirst = true;
    $scope.billList = [];   //票据列表

    //本地存储没有userId
    if(storage.getItem("userId")== null) {
        //调用微信接口获取code
        if (!Biostime.common.getQueryString("code")) {
            window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa0c751d21bc486d1&redirect_uri=" + encodeURIComponent(window.location.href) + "&response_type=code&scope=snsapi_base#wechat_redirect";
        } else {
            //由code获取userId
            getUserId($scope, $http);
        }
    }else{
        $scope.userId = JSON.parse(storage.userId);
    }

    //获取signature
    getSignature($scope,$http).then(function (success){
        //配置微信和企业微信
        wx.config({
            debug: false,                   //开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            appId: 'wxa0c751d21bc486d1',    //企业微信的cropID
            timestamp: $scope.timestamp,    //生成签名的时间戳
            nonceStr: $scope.noncestr,      //生成签名的随机串
            signature: $scope.signature,    //签名
            jsApiList: [                    //需要使用的JS接口列表
                'scanQRCode'
            ]
        });
    },function(error){});

    //扫描二维码并返回结果
    document.querySelector('#scanQRCode1').onclick = function () {
        wx.scanQRCode({
            desc: 'scanQRCode desc',
            needResult: 1, // 默认为0，扫描结果由企业微信处理，1则直接返回扫描结果，
            scanType: ["qrCode", "barCode"], // 可以指定扫二维码还是一维码，默认二者都有
            success: function(res) {
                var temp;
                var num;
                //企业微信
                if(res.resultStr.indexOf(",") == -1){
                    temp = res.resultStr;
                }else{
                    num = res.resultStr.indexOf(",");
                    temp = res.resultStr.substring(num+1);
                }
                for(var i=0; i<$scope.billList.length; i++){
                    if(temp == $scope.billList[i].bill){
                        break;
                    }
                }
                if(!(i<$scope.billList.length)){
                    $scope.billList.push({'bill':temp});
                }
                $scope.$apply();
            },
            error: function(res) {
                if (res.errMsg.indexOf('function_not_exist') > 0) {
                    alert('版本过低请升级');
                }
            }
        });
    };

    //删除某条票据信息
    $scope.delItem = function (num) {
        $scope.billList.splice(num,1);
    };

    //手动增加票据信息
    $scope.addBill = function () {
        if(!Biostime.common.isEmpty($scope.billNum)){
            for(var i=0; i<$scope.billList.length; i++){
                if($scope.billNum == $scope.billList[i].bill){
                    break;
                }
            }
            if(!(i<$scope.billList.length)){
                $scope.billList.push({'bill':$scope.billNum});
            }
            $scope.billNum = "";
            $scope.$apply();
        }
    };

    //提交票据
    $scope.submit = function(){
        if($scope.billList.length > 1){
            for(var i=1; i<$scope.billList.length; i++){
                if($scope.billList[i].bill.substring(0,2) != "BX" && $scope.billList[i].bill.substring(0,2) != "HX"){
                    Biostime.common.toastMessage("请确认输入的第1行是快递号，第2行以后是单据号。");
                    return;
                }
            }
        }
        submitBill($scope, $http, $sce);
    };

    //取消
    $scope.cancel = function () {
        $scope.billList = [];
    };

    //确认票据提交后，提示信息关闭
    $scope.makeSure = function () {
        $scope.isFirst = true;
        $scope.noticeShow = false;
        $scope.billList = [];
    }
}]);
/**
 * 由code获取userId
 * @param $scope
 * @param $http
 */
function getUserId($scope, $http){
    Biostime.common.showLoading();
    var queryData = {
        code: Biostime.common.getQueryString("code")
    };
    var url = BASICURL + "dealerPlatform/setting/getWxUserIdContact.do";
    $http({
        method: 'GET',
        url: url,
        params: queryData
    }).success(function (data, status, headers, config) {
        Biostime.common.hideLoading();
        $scope.userId = data.UserId;
        storage.setItem("userId", JSON.stringify($scope.userId));  //将userId放在本地存储中
    }).error(function(data, status, headers, config) {
        Biostime.common.hideLoading();
        Biostime.common.showErrorMessage(status);
    });
}
/**
 * 调用微信接口，获取signature
 * @param $scope
 * @param $http
 */
function getSignature($scope,$http){
    Biostime.common.showLoading();
    var url = BASICURL + "dealerPlatform/setting/getSignature.do?url=" + window.location.href;
    return $http({
        method: 'GET',
        url: url
    }).success(function (data, status, headers, config) {
        Biostime.common.hideLoading();
        $scope.noncestr = data.noncestr;        //生成签名的随机串
        $scope.signature = data.signature;      //签名
        $scope.timestamp = data.timestamp;      //生成签名的时间戳
    }).error(function(data, status, headers, config) {
        Biostime.common.hideLoading();
        Biostime.common.showErrorMessage(status);
    });
}
/**
 * 提交票据列表
 * @param $scope
 * @param $http
 */
function submitBill($scope, $http,$sce){
    Biostime.common.showLoading();
    var url = BASICURL + "dealerPlatform/bxtracking/save.do";
    var list=[];
    for(var i=0; i<$scope.billList.length; i++){
        list.push($scope.billList[i].bill);
    }
    var queryData = {
        "billList":list,
        "userId": $scope.userId
    };
    $http({
        method: 'POST',
        url: url,
        data: queryData
    }).success(function (data, status, headers, config) {
        Biostime.common.hideLoading();
        $scope.num = $scope.billList.length;
        $scope.isFirst =false;
        $scope.noticeShow = true;
        if(data.result == 0){
            $scope.isOk = true;
            $scope.desc = $sce.trustAsHtml(data.msg);
        }else{
            $scope.isOk = false;
            $scope.desc = $sce.trustAsHtml(data.msg);
        }
    }).error(function(data, status, headers, config) {
        Biostime.common.hideLoading();
        Biostime.common.showErrorMessage(status);
    });
}

