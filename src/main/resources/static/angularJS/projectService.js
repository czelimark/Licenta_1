(function () {
    'use strict';

    angular
        .module('app')
        .factory('ProjectService', ProjectService);

    ProjectService.$inject = ['$http'];
    function ProjectService($http) {
        var service = {};

        service.Create = Create;
        service.Delete = Delete;
        service.GetAllProjects = GetAllProjects;
        service.Update = Update;
        service.UpdateProjectTable = UpdateProjectTable;

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

        function UpdateProjectTable(project, portfolioId, walletId, callback) {
            return $http({
                method: 'PUT',
                url: '/app/updateProjectTable/' + portfolioId + '/' + walletId,
                data: project, portfolioId, walletId,
                headers: {
                    "Content-Type": "application/json;charset=UTF-8"
                }
            })
                .then(function (response) {
                    callback(response);
                });
        }

        function GetAllProjects(portfolio) {
            return $http({
                method: 'GET',
                url: '/app/projects/' + portfolio,
                data: portfolio,
                headers: {
                    "Content-Type": "application/json;charset=UTF-8"
                }
            })
                .then(handleSuccess, handleError('Error getting projects'));
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