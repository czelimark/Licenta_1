(function () {
    'use strict';

    angular
        .module('app')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['HomeService', '$window', '$rootScope', 'FlashService'];
    function HomeController(HomeService, $window, $rootScope, FlashService) {
        var vm = this;

        vm.user = null;

        getUserByUsername();
        vm.getUserByUsername = getUserByUsername;
        vm.updatePassword = updatePassword;
        vm.clearFields = clearFields;
        vm.updateUser = updateUser;

        function getUserByUsername() {
            vm.dataLoading = true;
            HomeService.GetUserByUsername($rootScope.globals.currentUser.username, function(user) {
                    if(user) {
                        vm.user = user.data;
                        if (user.data.gender == false)
                            vm.user.gender = "Male";
                        else
                            vm.user.gender = "Female";
                        vm.user.birthDate = new Date(user.data.birthDate);
                    }
                    else {
                        FlashService.Error("Error getting user");
                        vm.dataLoading = false;
                    }
                });
        }

        function updatePassword() {
            vm.dataLoading = true;
            if(vm.newPassword != vm.confirmPassword) {
                FlashService.Error("New Password and Confirm Password must be the same");
                vm.dataLoading = false;
            }
            HomeService.Update(vm.password, vm.newPassword, vm.user.username, function (response) {
                if (response.status == 200) {
                    $window.location.reload();
                } else {
                    FlashService.Error("Password does not match current password");
                    vm.dataLoading = false;
                }
            });
        }

        function clearFields() {
            vm.password = '';
            vm.newPassword = '';
            vm.confirmPassword = '';
        }

        function updateUser(profilePhoto) {
            vm.dataLoading = true;
            HomeService.updateUserr(profilePhoto, $rootScope.globals.currentUser.username, function (response) {
                if (response.status == 200) {
                } else {
                    FlashService.Error("Profile picture couldn't be updated");
                    vm.dataLoading = false;
                }
            });
        }

        let readURL = function(input) {
            if (input.files && input.files[0]) {
                let reader = new FileReader();

                reader.onload = function (e) {
                    $('.avatar').attr('src', e.target.result);
                    updateUser($('.avatar').attr('src'));
                }

                reader.readAsDataURL(input.files[0]);
            }
        }

        $(".inputFile").on('change', function(){
            readURL(this);
        });

    }

})();