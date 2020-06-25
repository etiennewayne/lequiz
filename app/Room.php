<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Room extends Model
{
    //

    protected $table = 'rooms';
    protected $primaryKey = 'room_id';


    protected $fillable = [
        'room', 'room_desc', 'access_code', 'quiz_id'
    ];


   

    

}
