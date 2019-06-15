(function () {
    'use strict';

    angular
        .module('app')
        .controller('PortfolioController', PortfolioController);

    PortfolioController.$inject = ['PortfolioService', '$window', '$rootScope', 'FlashService'];
    function PortfolioController(PortfolioService, $window, $rootScope, FlashService) {
        var vm = this;

        vm.portfolio = {};
        vm.portfolios = [];

        loadCurrentUserPortfolios();
        vm.addPortfolio = addPortfolio;
        vm.deletePortfolio = deletePortfolio;
        vm.populateForm = populateForm;
        vm.updatePortfolio = updatePortfolio;
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
        $rootScope.noOfPages = Math.ceil(portfolios.count() / $rootScope.numPerPage);
        $rootScope.currentPage = 1;

        $rootScope.setPage = function () {
            $rootScope.data = portfolios.get( ($rootScope.currentPage - 1) * $rootScope.numPerPage, $rootScope.numPerPage );
        };

        $rootScope.$watch( 'currentPage', $rootScope.setPage );*/

        function loadCurrentUserPortfolios() {
            PortfolioService.GetAllPortfolios($rootScope.globals.currentUser.username)
                .then(function (portfolios) {
                    vm.portfolios = portfolios;
                });
        }

        function addPortfolio() {
            vm.dataLoading = true;
            if(vm.portfolio.id) {
                FlashService.Error("ID duplicated");
                vm.dataLoading = false;
            }
            else {
                PortfolioService.Create(vm.portfolio, $rootScope.globals.currentUser.user, function (response) {
                    if (response.status == 200) {
                        FlashService.Success('Portfolio added');
                        $window.location.reload();
                    } else {
                        FlashService.Error(response.message);
                        vm.dataLoading = false;
                    }
                });
            }
        }

        function deletePortfolio(portfolio) {
            let deleteUser = $window.confirm('Are you sure you want to delete the portfolio?');
            if(deleteUser) {
                vm.dataLoading = true;
                PortfolioService.Delete(portfolio.id, function (response) {
                    if (response.status == 200) {
                        $window.location.reload();
                    } else {
                        FlashService.Error(response.message);
                        vm.dataLoading = false;
                    }
                });
            }
        }

        function populateForm(portfolio) {
            vm.portfolio.id = portfolio.id;
            vm.portfolio.portfolioName = portfolio.portfolioName;
            vm.portfolio.description = portfolio.description;
            vm.portfolio.issueDate = new Date(portfolio.issueDate);
        }

        function updatePortfolio() {
            vm.dataLoading = true;
            PortfolioService.Update(vm.portfolio, function (response) {
                if (response.status == 200) {
                    $window.location.reload();
                } else {
                    FlashService.Error(response.message);
                    vm.dataLoading = false;
                }
            });
        }

        function clearFields() {
            vm.portfolio.id = '';
            vm.portfolio.portfolioName = '';
            vm.portfolio.description = '';
            vm.portfolio.issueDate = '';
        }
    }

})();