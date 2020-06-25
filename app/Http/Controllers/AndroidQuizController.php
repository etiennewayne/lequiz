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
}



