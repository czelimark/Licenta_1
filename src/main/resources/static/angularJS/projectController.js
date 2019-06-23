(function () {
    'use strict';

    angular
        .module('app')
        .controller('ProjectController', ProjectController);

    ProjectController.$inject = ['ProjectService', 'WalletService', '$location', '$window', '$rootScope', 'FlashService'];
    function ProjectController(ProjectService, WalletService, $location, $window, $rootScope, FlashService) {
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
        //getPortfolio();
        vm.addProject = addProject;
        vm.deleteProject = deleteProject;
        vm.populateForm = populateForm;
        vm.updateProject = updateProject;
        vm.clearFields = clearFields;
        vm.projectDetails = projectDetails;

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

        function loadCurrentPortfolioProjects() {
            if(!$rootScope.globals.portfolio) {
                ProjectService.GetAllProjects($rootScope.$id)
                    .then(function (projects) {
                        vm.projects = projects;
                    });
            }
            else {
                ProjectService.GetAllProjects($rootScope.globals.portfolio.id)
                    .then(function (projects) {
                        vm.projects = projects;
                    });
            }
        }

        function loadWallets() {
            WalletService.GetAllWallets($rootScope.globals.currentUser.user)
                .then(function (wallets) {
                    vm.wallets = wallets
                });
        }

        function addProject() {
            vm.dataLoading = true;
            if(vm.project.id) {
                FlashService.Error("ID duplicated");
                vm.dataLoading = false;
            }
            else {
                ProjectService.Create(vm.project, function (response) {
                    if (response.status == 200) {
                        FlashService.Success('Project added');
                        $window.location.reload();
                    } else {
                        FlashService.Error(response.message);
                        vm.dataLoading = false;
                    }
                });
            }
        }

        function deleteProject(project) {
            let deleteProject = $window.confirm('Are you sure you want to delete the project?');
            if(deleteProject) {
                vm.dataLoading = true;
                ProjectService.Delete(project.id, function (response) {
                    if (response.status == 200) {
                        $window.location.reload();
                    } else {
                        FlashService.Error(response.message);
                        vm.dataLoading = false;
                    }
                });
            }
        }

        function populateForm(project) {
            vm.project.id = project.id;
            vm.project.projectName = project.projectName;
            vm.project.description = project.description;
            vm.project.issueDate = new Date(project.issueDate);
            vm.project.closingDate = new Date(project.closingDate);
            vm.project.estimatedPrice = project.estimatedPrice;
            vm.project.portfolio.id = project.portfolio.id;
            vm.project.comments = project.comments;
        }

        function updateProject() {
            vm.dataLoading = true;
            ProjectService.Update(vm.project, function (response) {
                if (response.status == 200) {
                    $window.location.reload();
                } else {
                    FlashService.Error(response.message);
                    vm.dataLoading = false;
                }
            });
        }

        function clearFields() {
            vm.project.id = '';
            vm.project.projectName = '';
            vm.project.description = '';
            vm.project.issueDate = '';
            vm.project.closingDate = '';
            vm.project.estimatedPrice = '';
            vm.project.comments = '';
        }

        function projectDetails() {
            $location.path("/projectDetails")
        }
    }

})();