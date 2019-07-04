(function () {
    'use strict';

    angular
        .module('app')
        .factory('HomeService', HomeService);

    HomeService.$inject = ['$http'];
    function HomeService($http) {
        var service = {};

        service.Update = Update;
        service.GetUserByUsername = GetUserByUsername;
        service.updateUserr = updateUserr;
        service.Send = Send;

        return service;

        function Update(password, newPassword, username, callback) {
            return $http({
                method: 'PUT',
                url: '/app/password' + '?' + "password=" + password + "&" + "newPassword=" + newPassword,
                data: password, newPassword, username,
                headers: {
                    "Content-Type": "application/json;charset=UTF-8"
                }
            })
                .then(function (response) {
                    callback(response);
                });
        }

        function GetUserByUsername(username, callback) {
            return $http({
                method: 'GET',
                url: '/app/user',
                data: username,
                headers: {
                    "Content-Type": "application/json;charset=UTF-8"
                }
            })
                .then(function (response) {
                    callback(response)
                });
        }

        function updateUserr(user, username, callback) {
            return $http({
                method: 'PUT',
                url: '/app/update',
                data: user, username,
                headers: {
                    "Content-Type": "application/json;charset=UTF-8"
                }
            })
                .then(function (response) {
                    callback(response);
                });
        }

        function Send(user, to, callback) {
            return $http({
                method: 'GET',
                url: '/app/email/' + to,
                data: user, to,
                headers: {
                    "Content-Type": "application/json;charset=UTF-8"
                }
            })
                .then(function (response) {
                    callback(response)
                });
        }

        // private functions

        function handleSuccess(res) {
            return res.data;
        }

        function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
    }

})();