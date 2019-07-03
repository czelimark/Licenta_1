(function () {
    'use strict';

    angular
        .module('app')
        .controller('ProjectsDetailsController', ProjectsDetailsController);

    ProjectsDetailsController.$inject = ['ProjectService', 'WalletService', '$window', '$rootScope', 'FlashService'];
    function ProjectsDetailsController(ProjectService, WalletService, $window, $rootScope, FlashService) {
        var vm = this;

        vm.project = {};
        vm.projects = [];
        vm.wallets = [];
        vm.saveProject = saveProject;
        vm.updateProject = updateProject;
        vm.deleteProject = deleteProject;

        $rootScope.$on("portfolio", function (event, data) {
            $rootScope.globals.portfolio = data;
        });

        $rootScope.$on("project", function (event, data) {
            $rootScope.globals.projects = data;
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

        vm.projects = $rootScope.globals.projects;

        $rootScope.$on("project1", function (event, data) {
            $rootScope.globals.project = data.project;
            $rootScope.globals.portfolioId = data.portfolioId;
            $rootScope.globals.walletId = data.walletId;
            $rootScope.globals.row = data.row;
        });

        function updateProject() {
            vm.dataLoading = true;
            ProjectService.UpdateProjectTable($rootScope.globals.project, $rootScope.globals.portfolioId, $rootScope.globals.walletId, function (response) {
                if (response.status == 200) {
                    loadCurrentPortfolioProjects();
                }
                else {
                    FlashService.Error(response.message);
                    vm.dataLoading = false;
                }
            });
        }

        function saveProject(r) {
            vm.dataLoading = true;
            var project = {};
            var data = this.getDataAtRow(r);
            project.id = data[0];
            project.projectName = data[1];
            project.description = data[2];
            project.issueDate = data[3];
            project.closingDate = data[4];
            project.estimatedPrice = data[5];
            project.actualPrice = data[6];
            project.difference = data[7];
            project.comments = data[9];
            project.portfolio = null;
            project.wallet = null;
            $rootScope.$broadcast('project1', {project: project, portfolioId: data[10], walletId: data[11], row: r});
        }

        $rootScope.$on("project2", function (event, data) {
            $rootScope.globals.projectId = data;
        });

        function deleteProject() {
            let deleteProject = $window.confirm('Are you sure you want to delete the project?');
            if(deleteProject) {
                vm.dataLoading = true;
                ProjectService.Delete($rootScope.globals.project.id, function (response) {
                    if (response.status == 200) {
                        hot.alter('remove_row', $rootScope.globals.project.row, $rootScope.globals.project.row)
                        loadCurrentPortfolioProjects();
                    } else {
                        FlashService.Error(response.message);
                        vm.dataLoading = false;
                    }
                });
            }
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
                                Object.assign(dataObject2, {currency: Object.values(vm.projects[key][key2]['currency']['currencyName'])});
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
                    data: 'currency',
                    type: 'text',
                    readOnly: true
                },
                {
                    data: 'comments',
                    type: 'text'
                },
                {
                    data: 'portfolioId',
                    type: 'text',
                    readOnly: true
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
                'Currency',
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