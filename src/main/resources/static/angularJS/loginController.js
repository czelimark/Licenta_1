(function () {
    'use strict';

    angular
        .module('app')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$location', 'AuthenticationService', 'FlashService'];
    function LoginController($location, AuthenticationService, FlashService) {
        let vm = this;

        vm.login = login;

        (function initController() {
            // reset login status
            AuthenticationService.ClearCredentials();
        })();

        function login() {
            vm.dataLoading = true;
            AuthenticationService.Login(vm.username, vm.password, function (response) {
                if (!(response.data.indexOf("Bad") > -1)) {
                    AuthenticationService.SetCredentials(vm.username, vm.password);
                    $location.path('/portfolios');
                } else {
                    FlashService.Error("Your login attempts was incorrect, pleas try again");
                    vm.dataLoading = false;
                }
            });
        };
    }

})();