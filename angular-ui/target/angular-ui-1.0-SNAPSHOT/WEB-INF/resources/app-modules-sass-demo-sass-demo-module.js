(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["app-modules-sass-demo-sass-demo-module"],{

/***/ "./src/app/modules/sass-demo/sass-demo.component.html":
/*!************************************************************!*\
  !*** ./src/app/modules/sass-demo/sass-demo.component.html ***!
  \************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div class=\"container-fluid\">\r\n  <div class=\"row\">\r\n    <div class=\"col-auto left-menu\">\r\n      <div id=\"sidebar-wrapper\">\r\n        <ul class=\"sidebar-nav\">\r\n          <li class=\"sidebar-brand\">\r\n            <a href=\"#\">\r\n              Sass Demo\r\n            </a>\r\n          </li>\r\n          <li>\r\n            <a routerLink=\"/sass-demo/variables\" routerLinkActive=\"active\">Variables</a>\r\n          </li>\r\n          <li>\r\n            <a href=\"#\">Shortcuts</a>\r\n          </li>\r\n          <li>\r\n            <a href=\"#\">Overview</a>\r\n          </li>\r\n          <li>\r\n            <a href=\"#\">Events</a>\r\n          </li>\r\n          <li>\r\n            <a href=\"#\">About</a>\r\n          </li>\r\n          <li>\r\n            <a href=\"#\">Services</a>\r\n          </li>\r\n          <li>\r\n            <a href=\"#\">Contact</a>\r\n          </li>\r\n        </ul>\r\n      </div>\r\n    </div>\r\n    <div class=\"col\">\r\n      <router-outlet></router-outlet>\r\n    </div>\r\n  </div>\r\n</div>\r\n\r\n"

/***/ }),

/***/ "./src/app/modules/sass-demo/sass-demo.component.scss":
/*!************************************************************!*\
  !*** ./src/app/modules/sass-demo/sass-demo.component.scss ***!
  \************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".left-menu {\n  width: 250px; }\n  .left-menu #sidebar-wrapper {\n    width: 250px; }\n"

/***/ }),

/***/ "./src/app/modules/sass-demo/sass-demo.component.ts":
/*!**********************************************************!*\
  !*** ./src/app/modules/sass-demo/sass-demo.component.ts ***!
  \**********************************************************/
/*! exports provided: SassDemoComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SassDemoComponent", function() { return SassDemoComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};

var SassDemoComponent = /** @class */ (function () {
    function SassDemoComponent() {
    }
    SassDemoComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-sass-demo',
            template: __webpack_require__(/*! ./sass-demo.component.html */ "./src/app/modules/sass-demo/sass-demo.component.html"),
            styles: [__webpack_require__(/*! ./sass-demo.component.scss */ "./src/app/modules/sass-demo/sass-demo.component.scss"), __webpack_require__(/*! ./simple-sidebar.css */ "./src/app/modules/sass-demo/simple-sidebar.css")]
        })
    ], SassDemoComponent);
    return SassDemoComponent;
}());



/***/ }),

/***/ "./src/app/modules/sass-demo/sass-demo.module.ts":
/*!*******************************************************!*\
  !*** ./src/app/modules/sass-demo/sass-demo.module.ts ***!
  \*******************************************************/
/*! exports provided: SassDemoModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SassDemoModule", function() { return SassDemoModule; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _websocket_websocket_module__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../websocket/websocket.module */ "./src/app/modules/websocket/websocket.module.ts");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _sass_demo_routing_module__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./sass-demo.routing.module */ "./src/app/modules/sass-demo/sass-demo.routing.module.ts");
/* harmony import */ var _sass_demo_component__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ./sass-demo.component */ "./src/app/modules/sass-demo/sass-demo.component.ts");
/* harmony import */ var _variales_variales_component__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./variales/variales.component */ "./src/app/modules/sass-demo/variales/variales.component.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};







var SassDemoModule = /** @class */ (function () {
    function SassDemoModule() {
    }
    SassDemoModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["NgModule"])({
            imports: [
                _sass_demo_routing_module__WEBPACK_IMPORTED_MODULE_4__["SassDemoRoutingModule"],
                _angular_common__WEBPACK_IMPORTED_MODULE_1__["CommonModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_3__["FormsModule"],
                _websocket_websocket_module__WEBPACK_IMPORTED_MODULE_2__["WebsocketModule"]
            ],
            declarations: [_sass_demo_component__WEBPACK_IMPORTED_MODULE_5__["SassDemoComponent"], _variales_variales_component__WEBPACK_IMPORTED_MODULE_6__["VarialesComponent"]]
        })
    ], SassDemoModule);
    return SassDemoModule;
}());



/***/ }),

/***/ "./src/app/modules/sass-demo/sass-demo.routing.module.ts":
/*!***************************************************************!*\
  !*** ./src/app/modules/sass-demo/sass-demo.routing.module.ts ***!
  \***************************************************************/
/*! exports provided: SassDemoRoutingModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SassDemoRoutingModule", function() { return SassDemoRoutingModule; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _sass_demo_component__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./sass-demo.component */ "./src/app/modules/sass-demo/sass-demo.component.ts");
/* harmony import */ var _variales_variales_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./variales/variales.component */ "./src/app/modules/sass-demo/variales/variales.component.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};




var routes = [
    {
        path: "",
        component: _sass_demo_component__WEBPACK_IMPORTED_MODULE_2__["SassDemoComponent"],
        children: [{
                path: 'variables',
                component: _variales_variales_component__WEBPACK_IMPORTED_MODULE_3__["VarialesComponent"]
            }]
    }
];
var SassDemoRoutingModule = /** @class */ (function () {
    function SassDemoRoutingModule() {
    }
    SassDemoRoutingModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["NgModule"])({
            exports: [_angular_router__WEBPACK_IMPORTED_MODULE_1__["RouterModule"]],
            imports: [_angular_router__WEBPACK_IMPORTED_MODULE_1__["RouterModule"].forChild(routes)]
        })
    ], SassDemoRoutingModule);
    return SassDemoRoutingModule;
}());



/***/ }),

/***/ "./src/app/modules/sass-demo/simple-sidebar.css":
/*!******************************************************!*\
  !*** ./src/app/modules/sass-demo/simple-sidebar.css ***!
  \******************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "/*!\r\n * Start Bootstrap - Simple Sidebar (https://startbootstrap.com/template-overviews/simple-sidebar)\r\n * Copyright 2013-2017 Start Bootstrap\r\n * Licensed under MIT (https://github.com/BlackrockDigital/startbootstrap-simple-sidebar/blob/master/LICENSE)\r\n */\r\n\r\nbody {\r\n  overflow-x: hidden;\r\n}\r\n\r\n#wrapper {\r\n  padding-left: 0;\r\n  transition: all 0.5s ease;\r\n}\r\n\r\n#wrapper.toggled {\r\n  padding-left: 250px;\r\n}\r\n\r\n#sidebar-wrapper {\r\n  z-index: 1000;\r\n  position: fixed;\r\n  left: 250px;\r\n  width: 0;\r\n  height: 100%;\r\n  margin-left: -250px;\r\n  overflow-y: auto;\r\n  background: #000;\r\n  transition: all 0.5s ease;\r\n}\r\n\r\n#wrapper.toggled #sidebar-wrapper {\r\n  width: 250px;\r\n}\r\n\r\n#page-content-wrapper {\r\n  width: 100%;\r\n  position: absolute;\r\n  padding: 15px;\r\n}\r\n\r\n#wrapper.toggled #page-content-wrapper {\r\n  position: absolute;\r\n  margin-right: -250px;\r\n}\r\n\r\n/* Sidebar Styles */\r\n\r\n.sidebar-nav {\r\n  position: absolute;\r\n  top: 0;\r\n  width: 250px;\r\n  margin: 0;\r\n  padding: 0;\r\n  list-style: none;\r\n}\r\n\r\n.sidebar-nav li {\r\n  text-indent: 20px;\r\n  line-height: 40px;\r\n}\r\n\r\n.sidebar-nav li a {\r\n  display: block;\r\n  text-decoration: none;\r\n  color: #999999;\r\n}\r\n\r\n.sidebar-nav li a:hover, .sidebar-nav li a.active {\r\n  text-decoration: none;\r\n  color: #fff;\r\n  background: rgba(255, 255, 255, 0.2);\r\n}\r\n\r\n.sidebar-nav li a:active, .sidebar-nav li a:focus {\r\n  text-decoration: none;\r\n}\r\n\r\n.sidebar-nav>.sidebar-brand {\r\n  height: 65px;\r\n  font-size: 18px;\r\n  line-height: 60px;\r\n}\r\n\r\n.sidebar-nav>.sidebar-brand a {\r\n  color: #999999;\r\n}\r\n\r\n.sidebar-nav>.sidebar-brand a:hover {\r\n  color: #fff;\r\n  background: none;\r\n}\r\n\r\n@media(min-width:768px) {\r\n  #wrapper {\r\n    padding-left: 0;\r\n  }\r\n  #wrapper.toggled {\r\n    padding-left: 250px;\r\n  }\r\n  #sidebar-wrapper {\r\n    width: 0;\r\n  }\r\n  #wrapper.toggled #sidebar-wrapper {\r\n    width: 250px;\r\n  }\r\n  #page-content-wrapper {\r\n    padding: 20px;\r\n    position: relative;\r\n  }\r\n  #wrapper.toggled #page-content-wrapper {\r\n    position: relative;\r\n    margin-right: 0;\r\n  }\r\n}\r\n"

/***/ }),

/***/ "./src/app/modules/sass-demo/variales/variables-core.component.scss":
/*!**************************************************************************!*\
  !*** ./src/app/modules/sass-demo/variales/variables-core.component.scss ***!
  \**************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".title {\n  font: 20px bold tahoma;\n  font-size: 20px;\n  color: red;\n  margin: 10px; }\n\n.circle-1 {\n  background-color: rgba(255, 0, 0, 0.1); }\n\n.circle-2 {\n  background-color: rgba(255, 0, 0, 0.2); }\n\n.circle-3 {\n  background-color: rgba(255, 0, 0, 0.3); }\n\n.circle-4 {\n  background-color: rgba(255, 0, 0, 0.4); }\n\n.circle-5 {\n  background-color: rgba(255, 0, 0, 0.5); }\n\n/*--------List---------*/\n\n.square-1 {\n  background-color: white; }\n\n.square-2 {\n  background-color: red; }\n\n.square-3 {\n  background-color: lightskyblue; }\n\n.square-4 {\n  background-color: yellow; }\n\n.square-5 {\n  background-color: #007d00; }\n\n/*-------Map--------*/\n\n.triangle-1 {\n  border-bottom-color: blue; }\n\n.triangle-2 {\n  border-bottom-color: yellow; }\n\n.triangle-3 {\n  border-bottom-color: tomato; }\n\n/*-----scope-----*/\n\n.end-text {\n  color: blue; }\n\nhr {\n  border-color: blue; }\n"

/***/ }),

/***/ "./src/app/modules/sass-demo/variales/variales.component.html":
/*!********************************************************************!*\
  !*** ./src/app/modules/sass-demo/variales/variales.component.html ***!
  \********************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div>\r\n  <span class=\"title\">Draw circle, square</span>\r\n</div>\r\n<div class=\"\">\r\n  <div class=\"circle circle-1\"></div>\r\n  <div class=\"circle circle-2\"></div>\r\n  <div class=\"circle circle-3\"></div>\r\n  <div class=\"circle circle-4\"></div>\r\n  <div class=\"circle circle-5\"></div>\r\n</div>\r\n\r\n<div class=\"squares\">\r\n  <div class=\"square square-1\"></div>\r\n  <div class=\"square square-2\"></div>\r\n  <div class=\"square square-3\"></div>\r\n  <div class=\"square square-4\"></div>\r\n  <div class=\"square square-5\"></div>\r\n</div>\r\n\r\n<div class=\"triangles\">\r\n  <div class=\"triangle triangle-1\"></div>\r\n  <div class=\"triangle triangle-2\"></div>\r\n  <div class=\"triangle triangle-3\"></div>\r\n</div>\r\n\r\n<div>\r\n  <span class=\"end-text\">End variables</span>\r\n  <hr/>\r\n</div>\r\n"

/***/ }),

/***/ "./src/app/modules/sass-demo/variales/variales.component.scss":
/*!********************************************************************!*\
  !*** ./src/app/modules/sass-demo/variales/variales.component.scss ***!
  \********************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".circle {\n  border-radius: 50%; }\n\n.squares {\n  margin-top: 20px; }\n\n.square, .circle {\n  width: 50px;\n  height: 50px;\n  border: 1px solid #000000;\n  display: inline-block;\n  margin: 20px; }\n\n.triangle {\n  width: 0;\n  height: 0;\n  border-left: 25px solid transparent;\n  border-right: 25px solid transparent;\n  border-bottom: 50px solid;\n  display: inline-block;\n  margin: 20px; }\n"

/***/ }),

/***/ "./src/app/modules/sass-demo/variales/variales.component.ts":
/*!******************************************************************!*\
  !*** ./src/app/modules/sass-demo/variales/variales.component.ts ***!
  \******************************************************************/
/*! exports provided: VarialesComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "VarialesComponent", function() { return VarialesComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var VarialesComponent = /** @class */ (function () {
    function VarialesComponent() {
    }
    VarialesComponent.prototype.ngOnInit = function () {
    };
    VarialesComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-variales',
            template: __webpack_require__(/*! ./variales.component.html */ "./src/app/modules/sass-demo/variales/variales.component.html"),
            styles: [__webpack_require__(/*! ./variales.component.scss */ "./src/app/modules/sass-demo/variales/variales.component.scss"), __webpack_require__(/*! ./variables-core.component.scss */ "./src/app/modules/sass-demo/variales/variables-core.component.scss")]
        }),
        __metadata("design:paramtypes", [])
    ], VarialesComponent);
    return VarialesComponent;
}());



/***/ })

}]);
//# sourceMappingURL=app-modules-sass-demo-sass-demo-module.js.map