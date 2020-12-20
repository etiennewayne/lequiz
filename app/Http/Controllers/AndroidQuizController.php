<?php

namespace App\Http\Controllers;
use App\Quiz;

use Illuminate\Support\Facades\DB;
use Illuminate\Http\Request;


class AndroidQuizController extends Controller
{
    //

    public function index($username){
        $quizes = DB::table('quizes')
        ->where('username', $username)->get();
        return $quizes;
    }



    public function quizzes($user_id){
    	return \DB::table('quizzes as a')
        ->join('categories as b', 'a.category_id', 'b.category_id')
        ->join('academicyears as c', 'b.academic_year_id', 'c.academic_year_id')
        ->where('a.user_id', $user_id)
        ->where('c.active', 1)
    	->get();
    }

    public function store(Request $req){

        $data = \DB::table('categories')
        ->where('category', $req->category)
        ->where('user_id', $req->user_id)
        ->first();

        $cat_id = $data->category_id;

        //return $req->user_id;


        \DB::table('quizzes')->insert([
            'user_id' => $req->user_id,
            'category_id' => $cat_id,
            'access_code' => $req->access_code,
            'quiz_title' => strtoupper($req->quiz_title),
            'quiz_desc' => strtoupper($req->quiz_desc)
        ]);

        return ['status' => 'saved'];

    }


    public function update(Request $req){

        $data = \DB::table('categories')
        ->where('category', $req->category)
         ->where('user_id', $req->user_id)
        ->first();
        $cat_id = $data->category_id;


        $quiz = Quiz::find($req->quiz_id);
        $quiz->category_id = $cat_id;
        $quiz->quiz_title = $req->quiz_title;
        $quiz->quiz_desc = $req->quiz_desc;
        $quiz->save();

        return ['status' => 'updated'];

    }

    public function edit($quiz_id){
        return \DB::table('quizzes as a')
        ->join('categories as b', 'a.category_id', 'b.category_id')
        ->where('quiz_id', $quiz_id)
        ->get();
    }

    public function delete(Request $req){
    	Quiz::destroy($req->quiz_id);
    	return ['status' => 'deleted'];
    }


    public function myQuizzes(Request $req){

        $data = \DB::table('student_quizzes as a')
        ->join('users as b', 'a.user_id', 'b.user_id')
        ->join('quizzes as c', 'c.quiz_id', 'a.quiz_id')
        ->join('categories as d', 'c.category_id', 'd.category_id')
        ->join('academicyears as ay', 'ay.academic_year_id', 'd.academic_year_id')
        ->select('a.student_quiz_id', 'a.quiz_id','a.user_id', 'c.access_code',
            'b.username', 'b.lname', 'b.fname', 'b.mname',
            'c.quiz_title', 'c.quiz_desc', 'c.category_id', 'd.category',
            'ay.academic_year_id', 'ay.ay_code',  'ay.ay',
            'a.total_score', \DB::raw('concat(date_format(a.created_at, "%b-%d-%Y"), " ", time_format(a.created_at, "%h:%i %p")) as created_at'),
            \DB::raw('(select sum(equiv_score) from questions where questions.quiz_id = c.quiz_id) as total_points')
        )
        ->where('a.user_id', $req->userid)
        ->where('ay.active', 1)
        ->where('d.category', $req->course)
        ->get();
  
        return $data;
    }


    public function validateCode(Request $req){
        $data = \DB::table('quizzes')
        ->join('categories', 'categories.category_id', 'quizzes.category_id')
        ->join('academicyears', 'academicyears.academic_year_id', 'categories.')
        ->where('access_code', $req->access_code)
        ->get()
        ->take(1);

        return $data;
    }


    public function studentList($quizid){
        $data = \DB::table('student_quizzes as a')
        ->join('users as b', 'a.user_id', 'b.user_id')
        ->join('quizzes as c', 'a.quiz_id', 'c.quiz_id')
        ->select('a.student_quiz_id', 'a.user_id',
            'b.username', 'b.lname', 'b.fname', 'b.mname',
            'c.access_code', 'c.quiz_id',
            'c.quiz_title', 'c.quiz_desc',
            'a.total_score',\DB::raw('concat(date_format(a.created_at, "%b-%d-%Y"), " ", time_format(a.created_at, "%h:%i %p")) as created_at'),
            \DB::raw('(select sum(equiv_score) from questions where questions.quiz_id = c.quiz_id) as total_points')
        )
        ->where('a.quiz_id', $quizid)
        ->get();

        return $data;
    }

    public function teacherQuizzes(Request $req){

    }





}



