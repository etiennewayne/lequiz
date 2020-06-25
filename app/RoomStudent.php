<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class RoomStudent extends Model
{
    //

 	protected $table = 'room_students';
    protected $primaryKey = 'room_student_id';


    protected $fillable = [
        'room_id', 'user_id'
    ];

}
