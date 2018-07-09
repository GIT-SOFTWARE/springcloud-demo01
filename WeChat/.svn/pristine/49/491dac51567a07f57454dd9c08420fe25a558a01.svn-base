/*******************************************************************************
 * @jsname:billMenu_controller.js
 * @author:WangDongping
 * @date:2017-08-18
 * @use:票据扫码主页.js
 ******************************************************************************/
var ngApp = angular.module('ngApp', []).config(
    [ '$interpolateProvider', function($interpolateProvider) {
        $interpolateProvider.startSymbol('[[');
        $interpolateProvider.endSymbol(']]');
    } ]);
var storage = window.localStorage;
ngApp.controller('billMenuController',['$scope','$http','$timeout',function($scope,$http,$timeout){

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
        //判断是否有权限查看票据签收、电子发票登记
        getAuthority($scope, $http);
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

        //判断是否有权限查看票据签收、电子发票登记
        getAuthority($scope, $http);
    }).error(function(data, status, headers, config) {
        Biostime.common.hideLoading();
        Biostime.common.showErrorMessage(status);
    });
}
function getAuthority($scope, $http){
    Biostime.common.showLoading();
    var queryData = {
    };
    var url = BASICURL + "dealerPlatform/bxtracking/auth.do";
    $http({
        method: 'GET',
        url: url,
        params: queryData
    }).success(function (data, status, headers, config) {
        Biostime.common.hideLoading();
        $scope.authorityList = data;
        $scope.showMenu = false;
        for(var i=0; i<$scope.authorityList.length; i++){
            if($scope.userId == $scope.authorityList[i]){
                $scope.showMenu = true;
                break;
            }
        }
    }).error(function(data, status, headers, config) {
        Biostime.common.hideLoading();
        Biostime.common.showErrorMessage(status);
    });
}




