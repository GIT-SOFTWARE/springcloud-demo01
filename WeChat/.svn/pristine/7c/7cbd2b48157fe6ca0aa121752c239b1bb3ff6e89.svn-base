/*******************************************************************************
 * @jsname:myBill_controller.js
 * @author:WangDongping
 * @date:2017-08-16
 * @use:我的票据.js
 ******************************************************************************/
var ngApp = angular.module('ngApp', []).config(
    [ '$interpolateProvider', function($interpolateProvider) {
        $interpolateProvider.startSymbol('[[');
        $interpolateProvider.endSymbol(']]');
    } ]);
var storage = window.localStorage;
ngApp.controller('myBillController',['$scope','$http','$timeout',function($scope,$http,$timeout){

    //页面一开始展示
    $scope.isFirst = true;

    //本地存储没有userId
    if(storage.getItem("userId")== null) {
        //调用微信接口获取code
        if (!Biostime.common.getQueryString("code")) {
            window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa0c751d21bc486d1&redirect_uri=" + encodeURIComponent(window.location.href) + "&response_type=code&scope=snsapi_base#wechat_redirect";
        } else {
            //由code获取userId,再获取票据信息
            getUserId($scope, $http);
        }
    }else{
        $scope.userId = JSON.parse(storage.userId);
        //获取票据信息
        getBillList($scope, $http);
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
        storage.setItem("userId", JSON.stringify($scope.userId));

        //获取票据信息
        getBillList($scope, $http);
    }).error(function(data, status, headers, config) {
        Biostime.common.hideLoading();
        Biostime.common.showErrorMessage(status);
    });
}
/**
 * 获取票据信息
 * @param $scope
 * @param $http
 */
function getBillList($scope, $http){
    Biostime.common.showLoading();
    var queryData = {
        "userId":$scope.userId
    };
    var url = BASICURL + "dealerPlatform/bxtracking/list.do";
    $http({
        method: 'POST',
        url: url,
        data: queryData
    }).success(function (data, status, headers, config) {
        Biostime.common.hideLoading();
        $scope.billList = data;
    }).error(function(data, status, headers, config) {
        Biostime.common.hideLoading();
        Biostime.common.showErrorMessage(status);
    });
}


