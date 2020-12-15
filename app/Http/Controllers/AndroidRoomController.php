<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\RoomStudent;


class AndroidRoomController extends Controller
{
    //

    public function validateCode(Request $req){
        $data = \DB::table('quizzes')
        ->where('access_code', $req->access_code)
        ->get()
        ->take(1);

        return $data;
    }


    public function joinRoom($access_code, $user_id){

    	$data = \DB::table('quizzes')
    	->where('access_code', $access_code)
    	->first();

    	if($data->isStart > 0){

    		RoomStudent::create([
    			'room_id' => $data->room_id,
    			'user_id' => $user_id
    		]);

    		return 1;
    	}else{
    		return 0;
    	}

    	//return $data;
    }

    public function rooms(Request $req){
        $data = \DB::select('call proc_room_by_ay(?, ?)', array($req->aycode, $req->userid));
        return $data;
    }


    public function studentList($roomid){
        $data = \DB::table('student_quizzes as a')
        ->join('users as b', 'a.user_id', 'b.user_id')
        ->join('rooms as c', 'a.room_id', 'c.room_id')
        ->join('quizzes as d', 'c.quiz_id', 'd.quiz_id')
        ->select('a.student_quiz_id', 'a.user_id',
            'b.username', 'b.lname', 'b.fname', 'b.mname',
            'a.room_id', 'c.room', 'c.room_desc', 'c.access_code', 'c.quiz_id',
            'd.quiz_title', 'd.quiz_desc',
            'a.total_score'
        )
        ->where('a.room_id', $roomid)
        ->get();

        return $data;
    }


    public function myQuizzes($userid){
        $data = \DB::table('student_quizzes as a')
        ->join('users as b', 'a.user_id', 'b.user_id')
        ->join('rooms as c', 'a.room_id', 'c.room_id')
        ->join('quizzes as d', 'c.quiz_id', 'd.quiz_id')
        ->select('a.student_quiz_id', 'a.user_id',
            'b.username', 'b.lname', 'b.fname', 'b.mname',
            'a.room_id', 'c.room', 'c.room_desc', 'c.access_code', 'c.quiz_id',
            'd.quiz_title', 'd.quiz_desc',
            'a.total_score'
        )
        ->where('a.user_id', $userid)
        ->get();

        return $data;
    }










}
