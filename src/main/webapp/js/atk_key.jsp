/**************************************************
    Gestione eventi campi
**************************************************/

var riga_corrente  = 0;
var campo_corrente = '';
var tot_righe      = 1;

var arr_input_name = new Array ( "el" );

// --------------------------------
function atk_onFocus(el){

  if (el.type == 'text' 
  ||  el.type == 'password'
      ){
  
      el.select();
  }
  
  campo_corrente = el.name;

}
// --------------------------------
//
// --------------------------------
function atk_onblur(el){
}
// --------------------------------
//
// --------------------------------

var ib_key_ctrl = 0;



function jskup(el){

  key = window.event.keyCode;

  // *** [CTRL+V]
  if (key == 17 || key == 86){
    
    if ( ib_key_ctrl == 1){

        atk_paste();
        
        ib_key_ctrl = 0;

    }else {

        ib_key_ctrl = 1;
    }

    return true;
  }
  // *** FINE [CTRL+V]

}



function jsk(el){

  key = window.event.keyCode;


  // *** [esc]
  if (key == 27){
    
    atk_close();

    return false;
  }

  // *** [F2]
  if (key == 112){
    atk_f1( );
    return false;
  }
  // *** [F2]
  if (key == 113){
    atk_f2( );
    return false;
  }

  // *** [F3]
  if (key == 114){
    atk_f3( );
    return false;
  }


  if (el.id.indexOf('~') < 0){
  
      return true;
  }
  var arr_ = el.id.split('~');
  
  var input_name = arr_[0];
  var riga = arr_[1] ;


  // *** [sù/giù]
  if (key == 38 || key == 40){

      if (key == 38) riga = (riga * 1) - 1; // sù
      if (key == 40) riga = (riga * 1) + 1; // giu

      if (riga < 0){

        el_ = document.getElementById('el~'+(tot_righe - 1));
        el_.focus();

      }else if (riga == 0){
          
        el_ = document.getElementById('el~0');
        el_.focus();

      }else if (riga < tot_righe){
        el_ = document.getElementById('el~'+riga);
        el_.focus();

      }else {
        el_ = document.getElementById('el~0');
        el_.focus();
      }
      return false;
  }


  return true;

} // FINE atk_key

