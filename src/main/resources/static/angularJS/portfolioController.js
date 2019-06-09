(function () {
    'use strict';

    angular
        .module('app')
        .controller('PortfolioController', PortfolioController);

    PortfolioController.$inject = ['UserService', '$rootScope'];
    function PortfolioController(UserService, $rootScope) {
        var vm = this;

        vm.user = null;
        vm.portfolios = [];

        loadCurrentUser();
        loadCurrentUserPortfolios();

        function loadCurrentUser() {
            UserService.GetByUsername($rootScope.globals.currentUser.username)
                .then(function (user) {
                    vm.user = user;
                });
        }

        function loadCurrentUserPortfolios() {
            UserService.GetAllPortfolios($rootScope.globals.currentUser.username)
                .then(function (portfolios) {
                    vm.portfolios = portfolios;
                });
        }
    }

})();