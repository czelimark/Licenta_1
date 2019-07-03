(function () {
    'use strict';

    angular
        .module('app')
        .controller('CostController', CostController);

    CostController.$inject = ['CostService', 'ProjectService', 'WalletService', '$window', '$rootScope', 'FlashService'];
    function ProjectsDetailsController(CostService, ProjectService, WalletService, $window, $rootScope, FlashService) {
        var vm = this;

        vm.cost = {};
        vm.costs = [];
        vm.projects = [];
        vm.resources = [];
        vm.currencies = [];
        vm.saveCost = saveCost;
        vm.updateCost = updateCost;
        vm.deleteCost = deleteCost;

        $rootScope.$on("portfolio", function (event, data) {
            $rootScope.globals.portfolio = data;
        });

        $rootScope.$on("cost", function (event, data) {
            $rootScope.globals.costs = data;
        });

        vm.project.portfolio = $rootScope.globals.portfolio;

        loadCurrentPortfolioProjectsCosts();
        loadProjects();
        loadResources();
        loadCurrencies();

        function loadCurrentPortfolioProjectsCosts() {
            if(!$rootScope.globals.portfolio) {
                CostService.GetAllCosts($rootScope.$id)
                    .then(function (costs) {
                        vm.costs = costs;
                        $rootScope.$broadcast('cost', vm.costs);
                    });
            }
            else {
                CostService.GetAllCosts($rootScope.globals.portfolio.id)
                    .then(function (costs) {
                        vm.costs = costs;
                        $rootScope.$broadcast('cost', vm.costs);
                    });
            }
        }

        function loadProjects() {
            ProjectService.GetAllProjects($rootScope.globals.portfolio.id)
                .then(function (projects) {
                    vm.projects = projects
                });
        }

        function loadResources() {
            WalletService.GetAllWallets($rootScope.globals.currentUser.user)
                .then(function (wallets) {
                    vm.wallets = wallets
                });
        }

        function loadCurrencies() {
            WalletService.GetAllCurrencies()
                .then(function (currencies) {
                    vm.currencies = currencies
                });
        }

        vm.costs = $rootScope.globals.costs;

        $rootScope.$on("cost1", function (event, data) {
            $rootScope.globals.cost = data.cost;
            $rootScope.globals.projectId = data.projectId;
            $rootScope.globals.resourceId = data.resourceId;
            $rootScope.globals.currencyId = data.currencyId;
            $rootScope.globals.row = data.row;
        });

        function updateCost() {
            vm.dataLoading = true;
            CostService.UpdateCostTable($rootScope.globals.cost, $rootScope.globals.projectId, $rootScope.globals.resourceId, $rootScope.globals.currencyId, function (response) {
                if (response.status == 200) {
                    loadCurrentPortfolioProjectsCosts();
                }
                else {
                    FlashService.Error(response.message);
                    vm.dataLoading = false;
                }
            });
        }

        function saveCost(r) {
            vm.dataLoading = true;
            var cost = {};
            var data = this.getDataAtRow(r);
            cost.id = data[0];
            cost.project = null;
            cost.resource = null;
            cost.description = data[3];
            cost.quantity = data[4];
            cost.cost = data[5];
            cost.currency = null;
            $rootScope.$broadcast('cost1', {cost: cost, projectId: data[1], resourceId: data[2], currencyId: data[6], row: r});
        }

        $rootScope.$on("cost2", function (event, data) {
            $rootScope.globals.costId = data;
        });

        function deleteCost() {
            let deleteCost = $window.confirm('Are you sure you want to delete the cost?');
            if(deleteCost) {
                vm.dataLoading = true;
                CostService.Delete($rootScope.globals.cost.id, function (response) {
                    if (response.status == 200) {
                        hot.alter('remove_row', $rootScope.globals.cost.row, $rootScope.globals.cost.row)
                        loadCurrentPortfolioProjectsCosts();
                    } else {
                        FlashService.Error(response.message);
                        vm.dataLoading = false;
                    }
                });
            }
        }

        var dataObject = [];
        var dataObject2 = [];
        for(var key in vm.costs) {
            if(vm.costs.hasOwnProperty(key)) {
                for(var key2 in vm.costs[key]) {
                    if(vm.costs[key].hasOwnProperty(key2)) {
                        switch(key2) {
                            case "id": {
                                Object.assign(dataObject2, {id: vm.costs[key][key2]});
                                break;
                            }
                            case "project": {
                                Object.assign(dataObject2, {projectId: Object.values(vm.costs[key][key2])[0]});
                                break;
                            }
                            case "resource": {
                                Object.assign(dataObject2, {resourceId: Object.values(vm.costs[key][key2])[0]});
                                break;
                            }
                            case "description": {
                                Object.assign(dataObject2, {description: vm.costs[key][key2]});
                                break;
                            }
                            case "quantity": {
                                Object.assign(dataObject2, {quantity: vm.costs[key][key2]});
                                break;
                            }
                            case "cost": {
                                Object.assign(dataObject2, {cost: vm.costs[key][key2]});
                                break;
                            }
                            case "currency": {
                                Object.assign(dataObject2, {currency: Object.values(vm.costs[key][key2]['currency']['currencyName'])});
                                dataObject2.currency = dataObject2.currency.join("");
                                break;
                            }
                        }
                    }
                }
            }
            dataObject.push(dataObject2);
            dataObject2 = [];
        }
        var hotElement = document.querySelector('#hot');
        var hotElementContainer = hotElement.parentNode;
        var hotSettings = {
            data: dataObject,
            columns: [
                {
                    data: 'id',
                    type: 'numeric',
                    width: 40,
                    readOnly: true
                },
                {
                    data: 'project',
                    type: 'numeric',
                    readOnly: true
                },
                {
                    data: 'resource',
                    type: 'numeric',
                    readOnly: true
                },
                {
                    data: 'description',
                    type: 'text'
                },
                {
                    data: 'quantity',
                    type: 'numeric',
                },
                {
                    data: 'cost',
                    type: 'numeric',
                    numericFormat: {
                        pattern: '0.00'
                    }
                },
                {
                    data: 'currency',
                    type: 'text',
                }
            ],
            stretchH: 'all',
            width: 800,
            height: 500,
            manualRowResize: true,
            manualColumnResize: true,
            rowHeaders: true,
            colHeaders: [
                'ID',
                'Project',
                'Resource',
                'Description',
                'Quantity',
                'Price',
                'Currency'
            ],
            manualRowMove: true,
            manualColumnMove: true,
            contextMenu: true,
            filters: true,
            dropdownMenu: true,
            columnSorting: {
                indicator: true
            },
            autoColumnSize: {
                samplingRatio: 23
            },
            exportFile: true,
            className: 'htCenter',
            afterSelection: saveCost,
            licenseKey: 'non-commercial-and-evaluation'
        };
        var hot = new Handsontable(hotElement, hotSettings);
        document.getElementById("export-csv").addEventListener("click",
            function(event) { hot.getPlugin("exportFile").downloadFile("csv", {filename: "Handsontable CSV Export example"});})
        document.getElementById("export-string").addEventListener("click",
            function(event) {console.log(hot.getPlugin("exportFile").exportAsString("csv"));});
    }

})();