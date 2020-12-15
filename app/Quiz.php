<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Quiz extends Model
{
    //

    protected $table = 'quizzes';
    protected $primaryKey = 'quiz_id';


    protected $fillable = [
        'user_id', 'category_id', 'access_code', 'quiz_title', 'quiz_desc'
    ];


    public function user(){
        //return $this->hasOne('\App\User', 'user_id');
        //return $this->belongsTo('App\User', 'user_id', 'user_id');
        return $this->belongsTo('App\User', 'user_id');
    }


     public function category(){
        //return $this->hasOne('\App\User', 'user_id');
        //return $this->belongsTo('App\User', 'user_id', 'user_id');
        return $this->belongsTo('App\Category','category_id');
    }

}
