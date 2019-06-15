(function () {
    'use strict';

    angular
        .module('app')
        .controller('WalletController', WalletController);

    WalletController.$inject = ['WalletService', '$window', '$rootScope', 'FlashService'];
    function WalletController(WalletService, $window, $rootScope, FlashService) {
        var vm = this;

        vm.wallet = {};
        vm.wallets = [];
        vm.currency = {};
        vm.currencies = [];

        loadCurrentUserWallets();
        loadCurrencies()
        vm.addWallet = addWallet;
        vm.deleteWallet = deleteWallet;
        vm.populateForm = populateForm;
        vm.updateWallet = updateWallet;
        vm.clearFields = clearFields;

        /*return {
            get: function(offset, limit) {
                return data.slice( offset, offset+limit );
            },
            count: function() {
                return data.length;
            }
        };

        $rootScope.numPerPage = 5;
        $rootScope.noOfPages = Math.ceil(Wallets.count() / $rootScope.numPerPage);
        $rootScope.currentPage = 1;

        $rootScope.setPage = function () {
            $rootScope.data = Wallets.get( ($rootScope.currentPage - 1) * $rootScope.numPerPage, $rootScope.numPerPage );
        };

        $rootScope.$watch( 'currentPage', $rootScope.setPage );*/

        function loadCurrentUserWallets() {
            WalletService.GetAllWallets($rootScope.globals.currentUser.username)
                .then(function (wallets) {
                    vm.wallets = wallets;
                });
        }

        function loadCurrencies() {
            WalletService.GetAllCurrencies()
                .then(function (currencies) {
                   vm.currencies = currencies
                });
        }

        function addWallet() {
            vm.dataLoading = true;
            if(vm.wallet.id) {
                FlashService.Error("ID duplicated");
                vm.dataLoading = false;
            }
            else {
                WalletService.Create(vm.wallet, $rootScope.globals.currentUser.user, function (response) {
                    if (response.status == 200) {
                        FlashService.Success('Wallet added');
                        $window.location.reload();
                    } else {
                        FlashService.Error(response.message);
                        vm.dataLoading = false;
                    }
                });
            }
        }

        function deleteWallet(wallet) {
            let deleteUser = $window.confirm('Are you sure you want to delete the wallet?');
            if(deleteUser) {
                vm.dataLoading = true;
                WalletService.Delete(wallet.id, function (response) {
                    if (response.status == 200) {
                        $window.location.reload();
                    } else {
                        FlashService.Error(response.message);
                        vm.dataLoading = false;
                    }
                });
            }
        }

        function populateForm(wallet) {
            vm.wallet.id = wallet.id;
            vm.wallet.walletName = wallet.walletName;
            vm.wallet.description = wallet.description;
            vm.wallet.allocatedMoney = wallet.allocatedMoney;
            vm.wallet.currency = wallet.currency;
        }

        function updateWallet() {
            vm.dataLoading = true;
            WalletService.Update(vm.wallet, function (response) {
                if (response.status == 200) {
                    $window.location.reload();
                } else {
                    FlashService.Error(response.message);
                    vm.dataLoading = false;
                }
            });
        }

        function clearFields() {
            vm.wallet.id = '';
            vm.wallet.walletName = '';
            vm.wallet.description = '';
            vm.wallet.allocatedMoney = '';
            vm.wallet.currency = '';
        }
    }

})();