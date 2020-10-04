<?php

namespace App\Http\Controllers;
use App\User;
use Illuminate\Http\Request;
use Auth;

class AndroidLoginController extends Controller
{
    //

  public function checkLogin($user, $pwd){

        // $credentials = $request->only('email', 'password');

        // if (Auth::attempt($credentials)) {
        //     // Authentication passed...
        //     //return redirect()->intended('dashboard');
        //     return 'login';
        // }


        $user = User::where('username',$user)
        ->where('apwd', $pwd)->get();
        

       return $user;
       //return Response::json(['datapack'=>$datapack],200);
  }


  public function authenticate(Request $request)
  {
      $credentials = $request->only('username', 'password');

      if (Auth::attempt($credentials)) {
          // Authentication passed...
          //return redirect()->intended('dashboard');
        return 'login';
      }
      return $credentials;
  }




   // public function androidLogin(Request $req){

   //  $credentials = $req->only('user', 'pwd');

   //    if (Auth::attempt($credentials)) {
   //          // Authentication passed...
   //        //return redirect()->intended('dashboard');
   //      return 1;
   //    }else{
   //      return 0;
   //    }
   // // return $req;
   // }

  public function androidLogin(Request $req){

      $credentials = $req->only('username', 'password');

        if (Auth::attempt($credentials)) {
         
          // Authentication passed...

          $user = User::where('username',$req->username)
          ->get();

          return $user;
        }else{
          return [];
        }
     // return $req;
  }

}
