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

        return service;

        function Create(user, callback) {
            return $http({
                method: 'PUT',
                url: '/app/register',
                data: user,
                headers: {
                    "Content-Type": "application/json;charset=UTF-8"
                },
            })
                .then(function (response) {
                    callback(response);
                });
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

        // private functions

        function handleSuccess(res) {
            return res.status;
        }

        function handleError(error) {
            return function () {
                return { message: error };
            };
        }
    }

})();