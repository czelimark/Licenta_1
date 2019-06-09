(function () {
    'use strict';

    angular
        .module('app')
        .factory('UserService', UserService);

    UserService.$inject = ['$http'];
    function UserService($http) {
        var service = {};

        service.Create = Create;
        service.Update = Update;
        service.GetByUsername = GetByUsername;
        service.GetAllPortfolios = GetAllPortfolios;

        return service;

        function Create(user) {
            return $http({
                method: 'PUT',
                url: '/app/register',
                data: user,
                headers: {
                    "Content-Type": "application/json;charset=UTF-8"
                }
            })
                .then(handleSuccess, handleError('Error creating user'));
        }

        function Update(user) {
            return $http.put('/api/users/' + user.id, user).then(handleSuccess, handleError('Error updating user'));
        }

        function GetByUsername(user) {
            return $http({
                method: 'GET',
                url: '/app/user',
                data: user,
                headers: {
                    "Content-Type": "application/json;charset=UTF-8"
                }
            })
                .then(handleSuccess, handleError('Error getting user'));
        }

        function GetAllPortfolios(user) {
            return $http({
                method: 'GET',
                url: '/app/portfolios',
                data: user,
                headers: {
                    "Content-Type": "application/json;charset=UTF-8"
                }
            })
                .then(handleSuccess, handleError('Error getting portfolios'));
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