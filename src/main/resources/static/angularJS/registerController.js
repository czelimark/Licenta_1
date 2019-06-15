(function () {
    'use strict';

    angular
        .module('app')
        .controller('RegisterController', RegisterController);

    RegisterController.$inject = ['UserService', '$location', '$rootScope', 'FlashService'];
    function RegisterController(UserService, $location, $rootScope, FlashService) {
        var vm = this;

        $rootScope.options = [
            {value: 0, name: 'Male'},
            {value: 1, name: 'Female'},
        ];

        vm.register = register;

        function register() {
            vm.dataLoading = true;
            UserService.Create(vm.user, function(response) {
                    if (response.status == 200) {
                        FlashService.Success('Registration successful', true);
                        $location.path('/login');
                    } else {
                        FlashService.Error(response.message);
                        vm.dataLoading = false;
                    }
                });
        }
    }

})();