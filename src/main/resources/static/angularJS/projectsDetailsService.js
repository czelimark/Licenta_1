(function () {
    'use strict';

    angular
        .module('app')
        .factory('ProjectDetailsService', ProjectDetailsService);

    ProjectDetailsService.$inject = ['$http'];
    function ProjectDetailsService($http) {
        var service = {};

        service.Create = Create;
        service.Delete = Delete;
        service.Update = Update;

        return service;

        function Create(project, callback) {
            return $http({
                method: 'PUT',
                url: '/app/addProject',
                data: project,
                headers: {
                    "Content-Type": "application/json;charset=UTF-8"
                }
            })
                .then(function (response) {
                    callback(response);
                });
        }

        function Delete(project, callback) {
            return $http({
                method: 'DELETE',
                url: '/app/deleteProject/' + project,
                data: project,
                headers: {
                    "Content-Type": "application/json;charset=UTF-8"
                }
            })
                .then(function (response) {
                    callback(response);
                });
        }

        function Update(project, callback) {
            return $http({
                method: 'PUT',
                url: '/app/updateProject',
                data: project,
                headers: {
                    "Content-Type": "application/json;charset=UTF-8"
                }
            })
                .then(function (response) {
                    callback(response);
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