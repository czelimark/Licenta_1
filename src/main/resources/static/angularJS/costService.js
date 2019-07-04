(function () {
    'use strict';

    angular
        .module('app')
        .factory('CostService', CostService);

    CostService.$inject = ['$http'];
    function CostService($http) {
        var service = {};

        service.Create = Create;
        service.Delete = Delete;
        service.GetAllCosts = GetAllCosts;
        service.Update = Update;
        service.GetAllResources = GetAllResources;

        return service;

        function Create(cost, projectId, resourceId, currencyName, callback) {
            return $http({
                method: 'PUT',
                url: '/app/addCost/' + projectId + '/' + resourceId + '/' + currencyName,
                data: cost, projectId, resourceId, currencyName,
                headers: {
                    "Content-Type": "application/json;charset=UTF-8"
                }
            })
                .then(function (response) {
                    callback(response);
                });
        }

        function Delete(cost, callback) {
            return $http({
                method: 'DELETE',
                url: '/app/deleteCost/' + cost,
                data: cost,
                headers: {
                    "Content-Type": "application/json;charset=UTF-8"
                }
            })
                .then(function (response) {
                    callback(response);
                });
        }

        function Update(cost, projectId, resourceId, currencyName, callback) {
            return $http({
                method: 'PUT',
                url: '/app/updateCost/' + projectId + '/' + resourceId + '/' + currencyName,
                data: cost, projectId, resourceId, currencyName,
                headers: {
                    "Content-Type": "application/json;charset=UTF-8"
                }
            })
                .then(function (response) {
                    callback(response);
                });
        }

        function GetAllCosts(portfolio) {
            return $http({
                method: 'GET',
                url: '/app/costs/' + portfolio,
                data: portfolio,
                headers: {
                    "Content-Type": "application/json;charset=UTF-8"
                }
            })
                .then(handleSuccess, handleError('Error getting costs'));
        }

        function GetAllResources() {
            return $http({
                method: 'GET',
                url: '/app/resources',
                headers: {
                    "Content-Type": "application/json;charset=UTF-8"
                }
            })
                .then(handleSuccess, handleError('Error getting resources'));
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