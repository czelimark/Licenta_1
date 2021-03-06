(function () {
    'use strict';

    angular
        .module('app', ['ngRoute', 'ngCookies'])
        .config(config)
        .run(run);

    config.$inject = ['$routeProvider', '$locationProvider'];
    function config($routeProvider) {
        $routeProvider
            .when('/portfolios', {
                controller: 'PortfolioController',
                templateUrl: 'templates/portfolioView.html',
                controllerAs: 'vm'
            })

            .when('/wallets', {
                controller: 'WalletController',
                templateUrl: 'templates/walletView.html',
                controllerAs: 'vm'
            })

            .when('/login', {
                controller: 'LoginController',
                templateUrl: 'templates/loginView.html',
                controllerAs: 'vm'
            })

            .when('/register', {
                controller: 'RegisterController',
                templateUrl: 'templates/registerView.html',
                controllerAs: 'vm'
            })

            .when('/profile', {
                controller: 'HomeController',
                templateUrl: 'templates/homeView.html',
                controllerAs: 'vm'
            })

            .when('/nav-details', {
                controller: 'HomeController',
                templateUrl: 'templates/homeView.html',
                controllerAs: 'vm'
            })

            .when('/nav-invite', {
                controller: 'HomeController',
                templateUrl: 'templates/homeView.html',
                controllerAs: 'vm'
            })

            .when('/projects', {
                controller: 'ProjectController',
                templateUrl: 'templates/projectView.html',
                controllerAs: 'vm'
            })

            .when('/projectsDetails', {
                controller: 'ProjectsDetailsController',
                templateUrl: 'templates/projectsDetailsView.html',
                controllerAs: 'vm'
            })

            .when('/costs', {
                controller: 'CostController',
                templateUrl: 'templates/costView.html',
                controllerAs: 'vm'
            })

            .otherwise({ redirectTo: '/login' });
    }

    run.$inject = ['$rootScope', '$location', '$cookies', '$http'];
    function run($rootScope, $location, $cookies, $http) {
        // keep user logged in after page refresh
        $rootScope.globals = $cookies.getObject('globals') || {};
        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata;
        }

        $rootScope.$on('$locationChangeStart', function () {
            // redirect to login page if not logged in and trying to access a restricted page
            var restrictedPage = $.inArray($location.path(), ['/login', '/register']) === -1;
            var loggedIn = $rootScope.globals.currentUser;
            if (restrictedPage && !loggedIn) {
                $location.path('/login');
            }
        });
    }

})();