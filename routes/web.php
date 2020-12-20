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
Route::post('/account/reset-password/{id}','AccountPageController@reset');

Route::get('/account/ajax/users','AccountPageController@data_users');

Route::get('/academicyear/{academicyear}/markactive','AcademicYearController@markactive');
Route::resource('/academicyear','AcademicYearController');
Route::get('/academicyear/ajax/academicyears','AcademicYearController@data_ay');

//


Route::resource('/course','CourseController');



Route::resource('/category', 'CategoryController');
Route::get('/category/ajax/categories','CategoryController@categories');




//QUIZ HERE!!
Route::resource('/quiz','QuizController');
Route::get('/quiz/ajax/quizzes','QuizController@quizzes');

Route::resource('/quiz/{quizid}/question', 'QuestionController');
Route::get('/quiz/question/uploader/{quizid}', 'QuestionController@uploader')->name('question.uploader');
Route::post('/quiz/question/uploader/39/store', 'QuestionController@storeUpload');

Route::get('/quiz/student-quizzes/{quizid}', 'QuizController@studentQuizzes');
Route::get('/quiz/student-quizzes/ajax/studentquiz/{quizid}', 'QuizController@studentQuizzesAjax');






Route::get('/quiz/question/ajax/{quizid}', 'QuestionController@questions');
Route::get('/quiz/question/ajax/question-by-access-code/{acode}', 'QuestionController@questionsByAccessCode');

//START QUIZ
Route::get('/quiz/start/{acode}', 'QuizController@startQuiz');

Route::get('/quiz/question/ajax/question-by-access-code/{acode}', 'QuestionController@questionsByAccessCode');
Route::get('/android/quiz/my-quizzes/','AndroidQuizController@myQuizzes'); //my quizzes in android
Route::get('/android/student-list/', 'AndroidStudentListController@teacherQuizzes');




//Room route
Route::resource('/room','RoomController');
Route::get('/room/ajax/rooms','RoomController@rooms');
Route::get('/room/create/ajax/categories','RoomController@ajaxCategories');
Route::get('/room/lobby/{accesscode}','RoomController@lobbyusers');
Route::get('/room/create/ajax/quizzes/cid/{cid}','RoomController@ajaxQuizzes');


//Route::get('/android/login/{u}/{p}','AndroidLoginController@checkLogin');
//Route::get('/android/login/{user}{pwd}','AndroidLoginController@checkLogin');

//Route::get('/android/login','AndroidLoginController@androidLogin');
//Route::get('/android/auth','AndroidLoginController@authenticate');

//Route::get('/android/quizes/{u}','AndroidQuizController@index');

Route::post('/android/login','AndroidLoginController@androidLogin');


//VALIDATION OF THE GIVEN ACCESS CODE
Route::post('/android/validate/code', 'AndroidQuizController@validateCode');



Route::get('/android/room/join/{accesscode}/{userid}','AndroidRoomController@joinRoom');
Route::get('/android/room/getroom-ay','AndroidRoomController@rooms');




Route::get('/android/ay','AndroidAcademicYear@getAY');



Route::post('/android/category/store','AndroidCategoryController@store');
Route::get('/android/category/{uid}','AndroidCategoryController@getCategories');
Route::post('/android/category/delete','AndroidCategoryController@delete');
Route::get('/android/category/{catid}/edit','AndroidCategoryController@edit');
Route::post('/android/category/update','AndroidCategoryController@update');


Route::get('/android/quiz/{uid}','AndroidQuizController@quizzes');
Route::post('/android/quiz/store','AndroidQuizController@store');
Route::post('/android/quiz/delete','AndroidQuizController@delete');
Route::get('/android/quiz/{quiz_id}/edit','AndroidQuizController@edit');
Route::post('/android/quiz/update','AndroidQuizController@update');
Route::get('/android/quiz/student-list/{quizid}','AndroidQuizController@studentList');




Route::get('/android/question','AndroidQuestionController@questions');
Route::get('/android/question/{qid}/edit','AndroidQuestionController@edit');
Route::post('/android/question/update','AndroidQuestionController@update');
Route::post('/android/question/store','AndroidQuestionController@store');
Route::post('/android/question/delete','AndroidQuestionController@delete');
//save question in game android


Route::post('/android/quizgame/store','AndroidQuizGameController@store');



//RESULT
//get categories in QUIZ RESULT
Route::get('/android/myquizzes-category/{userid}','AndroidCategoryController@getCategoriesQuizzes');



