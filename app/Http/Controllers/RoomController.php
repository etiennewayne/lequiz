<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Category;
use App\Room;


class RoomController extends Controller
{
    //

    public function __construct()
    {
        $this->middleware('auth');
        $this->middleware('faculty');
    }


    public function index(){
        return view('room/room');
    }


    public function create(){
        //$quiz = \DB::table('quizzes')


        return view('room.roomcreate');
       // return $categories;
	}

	public function store(Request $req){
        $validate = $req->validate([
            'room' => ['required', 'string', 'max:50', 'unique:rooms'],
            'access_code' => ['required', 'string', 'max:10', 'unique:rooms'],
            'quiz_id' => ['required'],
        ]);

        $data = Room::create([
            'room' => strtoupper($req->room),
            'room_desc' => strtoupper($req->room_desc),
            'access_code' => $req->access_code,
            'quiz_id' => $req->quiz_id,

        ]);

        return redirect('/room')->with('success','Room successfully created.');

    }



    public function edit($id){

        $room = Room::find($id);

       return view('room.roomedit')
           ->with('room', $room);
    }

    public function update(Request $req, $id){

        $validate = $req->validate([
            'room' => ['required', 'string', 'max:50', 'unique:rooms'],
        ]);

        $data = Room::find($id);
        $data->room = $req->room;
        $data->room_desc = $req->room_desc;
        $data->save();

        return redirect('/room')->with('success','Room successfully updated.');
    }

    public function rooms(){
        $data = \DB::table('rooms as a')
        ->join('quizzes as b', 'a.quiz_id','b.quiz_id')
            ->where('b.user_id', \Auth::user()->user_id)
        ->select('a.room_id', 'a.room', 'a.room_desc', 'a.access_code', 'a.quiz_id', 'b.quiz_title', 'b.quiz_desc')
        ->get();
        return $data;
    }

    public function ajaxCategories(){
        return \DB::table('categories')->where('user_id', \Auth::user()->user_id)
            ->get();
    }

    public function ajaxQuizzes($cat_id){
        $user_id = \Auth::user()->user_id;
        return \DB::table('quizzes')->where('user_id', $user_id)
        ->where('category_id', $cat_id)
        ->get();

    }


    public function destroy($id){
        Room::destroy($id);
        return redirect('/room')
        ->with('deleted', 'Successfully deleted.');
    }



     public function lobbyusers($accesscode){
        //return $accesscode;

        return view('room.lobby', ['accesscode' => $accesscode]);
    }



}
