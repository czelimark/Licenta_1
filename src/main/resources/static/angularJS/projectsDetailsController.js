(function () {
    'use strict';

    angular
        .module('app')
        .controller('ProjectsDetailsController', ProjectsDetailsController);

    ProjectsDetailsController.$inject = ['ProjectService', 'WalletService', '$location', '$window', '$rootScope', 'FlashService'];
    function ProjectsDetailsController(ProjectService, WalletService, $location, $window, $rootScope, FlashService) {
        var vm = this;

        vm.project = {};
        vm.projects = [];
        vm.wallets = [];

        $rootScope.$on("portfolio", function (event, data) {
            $rootScope.globals.portfolio = data;
        });

        vm.project.portfolio = $rootScope.globals.portfolio;

        loadCurrentPortfolioProjects();
        loadWallets();

        function loadCurrentPortfolioProjects() {
            if(!$rootScope.globals.portfolio) {
                ProjectService.GetAllProjects($rootScope.$id)
                    .then(function (projects) {
                        vm.projects = projects;
                        $rootScope.$broadcast('project', vm.projects);
                    });
            }
            else {
                ProjectService.GetAllProjects($rootScope.globals.portfolio.id)
                    .then(function (projects) {
                        vm.projects = projects;
                        $rootScope.$broadcast('project', vm.projects);
                    });
            }
        }

        function loadWallets() {
            WalletService.GetAllWallets($rootScope.globals.currentUser.user)
                .then(function (wallets) {
                    vm.wallets = wallets
                });
        }

        $rootScope.$on("project", function (event, data) {
            $rootScope.globals.projects = data;
        });

        $rootScope.$on("project1", function (event, data) {
            $rootScope.globals.project = data;
        });

        vm.projects = $rootScope.globals.projects;
        vm.project = $rootScope.globals.project;

        function saveProject(r, c) {
            var data = this.getDataAtRow(r);
            $rootScope.$broadcast('project1', vm.project);
        }

        var dataObject = [];
        var dataObject2 = [];
        for(var key in vm.projects) {
            if(vm.projects.hasOwnProperty(key)) {
                for(var key2 in vm.projects[key]) {
                    if(vm.projects[key].hasOwnProperty(key2)) {
                        switch(key2) {
                            case "id": {
                                Object.assign(dataObject2, {id: vm.projects[key][key2]});
                                break;
                            }
                            case "projectName": {
                                Object.assign(dataObject2, {projectName: vm.projects[key][key2]});
                                break;
                            }
                            case "description": {
                                Object.assign(dataObject2, {description: vm.projects[key][key2]});
                                break;
                            }
                            case "issueDate": {
                                Object.assign(dataObject2, {issueDate: vm.projects[key][key2]});
                                break;
                            }
                            case "closingDate": {
                                Object.assign(dataObject2, {closingDate: vm.projects[key][key2]});
                                break;
                            }
                            case "estimatedPrice": {
                                Object.assign(dataObject2, {estimatedPrice: vm.projects[key][key2]});
                                break;
                            }
                            case "actualPrice": {
                                Object.assign(dataObject2, {actualPrice: vm.projects[key][key2]});
                                break;
                            }
                            case "difference" : {
                                Object.assign(dataObject2, {difference: vm.projects[key][key2]});
                                break;
                            }
                            case "comments" : {
                                Object.assign(dataObject2, {comments: vm.projects[key][key2]});
                                break;
                            }
                            case "portfolio": {
                                Object.assign(dataObject2, {portfolioId: Object.values(vm.projects[key][key2])[0]});
                                break;
                            }
                            case "wallet": {
                                Object.assign(dataObject2, {walletId: Object.values(vm.projects[key][key2])[0]});
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
                    width: 40
                },
                {
                    data: 'projectName',
                    type: 'text',
                },
                {
                    data: 'description',
                    type: 'text'
                },
                {
                    data: 'issueDate',
                    type: 'date',
                    dateFormat: 'MM/DD/YYYY'
                },
                {
                    data: 'closingDate',
                    type: 'date',
                    dateFormat: 'MM/DD/YYYY'
                },
                {
                    data: 'estimatedPrice',
                    type: 'numeric',
                    numericFormat: {
                        pattern: '0.00'
                    }
                },
                {
                    data: 'actualPrice',
                    type: 'numeric',
                    numericFormat: {
                        pattern: '0.00'
                    }
                },
                {
                    data: 'difference',
                    type: 'numeric',
                    numericFormat: {
                        pattern: '0.00'
                    }
                },
                {
                    data: 'comments',
                    type: 'text'
                },
                {
                    data: 'portfolioId',
                    type: 'text'
                },
                {
                    data: 'walletId',
                    type: 'text'
                }
            ],
            stretchH: 'all',
            width: 1138,
            height: 500,
            manualRowResize: true,
            manualColumnResize: true,
            rowHeaders: true,
            colHeaders: [
                'ID',
                'Project Name',
                'Description',
                'Issue Date',
                'Closing Date',
                'Estimated Price',
                'Actual Price',
                'Difference',
                'Comments',
                'Portfolio',
                'Wallet'
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
            afterSelection: saveProject,
            licenseKey: 'non-commercial-and-evaluation'
        };
        var hot = new Handsontable(hotElement, hotSettings);
        document.getElementById("export-csv").addEventListener("click",
            function(event) { hot.getPlugin("exportFile").downloadFile("csv", {filename: "Handsontable CSV Export example"});})
        document.getElementById("export-string").addEventListener("click",
            function(event) {console.log(hot.getPlugin("exportFile").exportAsString("csv"));});
    }

})();