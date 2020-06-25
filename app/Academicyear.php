<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Academicyear extends Model
{
    //

	protected $table = 'academicyears';

    protected $fillable = [
    	'ay_code', 'ay', 'semester_id'
    ];

    protected $primaryKey  = 'academic_year_id';


    
}
