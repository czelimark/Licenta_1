(function () {
    'use strict';

    angular
        .module('app')
        .factory('WalletService', WalletService);

    WalletService.$inject = ['$http'];
    function WalletService($http) {
        var service = {};

        service.Create = Create;
        service.Delete = Delete;
        service.GetAllWallets = GetAllWallets;
        service.Update = Update;
        service.GetAllCurrencies = GetAllCurrencies;
        service.GetWallet = GetWallet;

        return service;

        function Create(wallet, user, callback) {
            return $http({
                method: 'PUT',
                url: '/app/addWallet',
                data: wallet, user,
                headers: {
                    "Content-Type": "application/json;charset=UTF-8"
                }
            })
                .then(function (response) {
                    callback(response);
                });
        }

        function Delete(wallet, callback) {
            return $http({
                method: 'DELETE',
                url: '/app/deleteWallet/' + wallet,
                data: wallet,
                headers: {
                    "Content-Type": "application/json;charset=UTF-8"
                }
            })
                .then(function (response) {
                    callback(response);
                });
        }

        function Update(wallet, callback) {
            return $http({
                method: 'PUT',
                url: '/app/updateWallet',
                data: wallet,
                headers: {
                    "Content-Type": "application/json;charset=UTF-8"
                }
            })
                .then(function (response) {
                    callback(response);
                });
        }

        function GetAllWallets(user) {
            return $http({
                method: 'GET',
                url: '/app/wallets',
                data: user,
                headers: {
                    "Content-Type": "application/json;charset=UTF-8"
                }
            })
                .then(handleSuccess, handleError('Error getting wallets'));
        }

        function GetWallet(wallet) {
            return $http({
                method: 'GET',
                url: '/app/wallet/' + wallet,
                data: wallet,
                headers: {
                    "Content-Type": "application/json;charset=UTF-8"
                }
            })
                .then(handleSuccess, handleError('Error getting wallets'));
        }

        function GetAllCurrencies() {
            return $http({
                method: 'GET',
                url: '/app/currencies',
                headers: {
                    "Content-Type": "application/json;charset=UTF-8"
                }
            })
                .then(handleSuccess, handleError('Error getting currencies'));
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