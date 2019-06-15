(function () {
    'use strict';

    angular
        .module('app')
        .factory('PortfolioService', PortfolioService);

    PortfolioService.$inject = ['$http'];
    function PortfolioService($http) {
        var service = {};

        service.Create = Create;
        service.Delete = Delete;
        service.GetAllPortfolios = GetAllPortfolios;
        service.Update = Update;

        return service;

        function Create(portfolio, user, callback) {
            return $http({
                method: 'PUT',
                url: '/app/addPortfolio',
                data: portfolio, user,
                headers: {
                    "Content-Type": "application/json;charset=UTF-8"
                }
            })
                .then(function (response) {
                    callback(response);
                });
        }

        function Delete(portfolio, callback) {
            return $http({
                method: 'DELETE',
                url: '/app/deletePortfolio/' + portfolio,
                data: portfolio,
                headers: {
                    "Content-Type": "application/json;charset=UTF-8"
                }
            })
                .then(function (response) {
                    callback(response);
                });
        }

        function Update(portfolio, callback) {
            return $http({
                method: 'PUT',
                url: '/app/updatePortfolio',
                data: portfolio,
                headers: {
                    "Content-Type": "application/json;charset=UTF-8"
                }
            })
                .then(function (response) {
                    callback(response);
                });
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