<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\RoomStudent;


class AndroidRoomController extends Controller
{
    //

    public function validateCode(Request $req){
        $data = \DB::table('rooms')
        ->where('access_code', $req->access_code)
        ->get()
        ->take(1);

        return $data;
    }


    public function joinRoom($access_code, $user_id){
    	

    	$data = \DB::table('rooms')
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


}
