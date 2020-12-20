<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class AndroidStudentListController extends Controller
{
    //

    public function teacherQuizzes(Request $req){

        $userid = $req->userid;
        $aycode = $req->aycode;

        //GET ALL STUDENT QUIZZES BY TEACHER
        $data = DB::table('student_quizzes as a')
        ->join('quizzes as b', 'a.quiz_id', 'b.quiz_id')
        ->join('categories as c', 'b.category_id', 'c.category_id')
        ->join('academicyears as d', 'c.academic_year_id', 'd.academic_year_id')
        ->select('a.student_quiz_id', 'a.user_id', 'a.quiz_id',
            'b.access_code', 'b.quiz_title', 'b.quiz_desc',
            'c.category_id', 'category as course' , 'c.user_id as teacher_id', 'd.academic_year_id', 'd.ay_code'
        )
        ->where('d.ay_code', $aycode)
        ->where('c.user_id', $userid)
        ->get();

        return $data;

    }
}
