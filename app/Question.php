<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Question extends Model
{
    //

    //protected $table = 'questions';
    protected $table = 'questions';
    protected $primaryKey = 'question_id';
    public $timestamps = false;

    protected $fillable = [
        'question','opt_a', 'opt_b', 'opt_c', 'opt_d', 'ans', 'quiz_id', 'set_time', 'equiv_score'
    ];


}
