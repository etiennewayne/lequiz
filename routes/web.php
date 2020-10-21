<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

/*Route::get('/', function () {
    return view('welcome');
});

Route::get('/abapo', function () {
    return "AbAPO BAYOT";
});



Route::get('/custom-login','login/CustomLoginController@showForm');

Route::get('/test', 'login/CustomLoginController@test');

*/

Route::get('custom-login','login\CustomLoginController@showForm');

Route::get('/','PagesController@index');


Auth::routes();
Route::get('/home', 'HomeController@index')->name('home');

//Route::get('/main', 'MainPageController@index');

Route::resource('/account','AccountPageController');
Route::get('/account/ajax/users','AccountPageController@data_users');

Route::get('/academicyear/{academicyear}/markactive','AcademicYearController@markactive');
Route::resource('/academicyear','AcademicYearController');
Route::get('/academicyear/ajax/academicyears','AcademicYearController@data_ay');

//


Route::resource('/course','CourseController');



Route::resource('/category', 'CategoryController');
Route::get('/category/ajax/categories','CategoryController@categories');





Route::resource('/quiz','QuizController');
Route::get('/quiz/ajax/quizzes','QuizController@quizzes');

Route::resource('/quiz/{quizid}/question', 'QuestionController');
Route::get('/quiz/question/ajax/{quizid}', 'QuestionController@questions');
Route::get('/quiz/question/ajax/question-by-access-code/{acode}', 'QuestionController@questionsByAccessCode');











//Room route
Route::resource('/room','RoomController');
Route::get('/room/ajax/rooms','RoomController@rooms');
Route::get('/room/create/ajax/categories','RoomController@ajaxCategories');
Route::get('/room/lobby/{accesscode}','RoomController@lobbyusers');
Route::get('/room/create/ajax/quizzes/cid/{cid}','RoomController@ajaxQuizzes');


//Route::get('/android/login/{u}/{p}','AndroidLoginController@checkLogin');
//Route::get('/android/login/{user}{pwd}','AndroidLoginController@checkLogin');

Route::get('/android/login','AndroidLoginController@androidLogin');
//Route::get('/android/auth','AndroidLoginController@authenticate');

Route::get('/android/quizes/{u}','AndroidQuizController@index');

Route::get('/android/login','AndroidLoginController@androidLogin');

Route::post('/android/validate/code', 'AndroidRoomController@validateCode');

Route::get('/android/room/join/{accesscode}/{userid}','AndroidRoomController@joinRoom');


Route::get('/android/ay','AndroidAcademicYear@getAY');

//save question in game android
Route::post('/android/quizgame/store','AndroidQuizGameController@store');

