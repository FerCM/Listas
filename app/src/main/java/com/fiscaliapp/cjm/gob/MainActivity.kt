package com.fiscaliapp.cjm.gob

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fiscaliapp.cjm.gob.ui.theme.CJMTheme
import kotlin.math.exp

private val messages: List<MyMessage> = listOf(MyMessage("Hola Mundo", "Es mi primera app con Kotlin y Jetpack"),
                                        MyMessage("Hola Mundo", "Es mi primera app con Kotlin y Jetpack. En el año 1878 obtuve el título de doctor en medicina por la Universidad de Londres, asistiendo después en Netley a los cursos que son de rigor antes de ingresar como médico en el ejército. Concluidos allí mis estudios, fui puntualmente destinado el 5.0 de Fusileros de Northumberland en calidad de médico ayudante. El regimiento se hallaba por entonces estacionado en la India, y antes de que pudiera unirme a él, estalló la segunda guerra de Afganistán."),
                                        MyMessage("Hola Mundo", "Es mi primera app con Kotlin y Jetpack En el año 1878 obtuve el título de doctor en medicina por la Universidad de Londres, asistiendo después en Netley a los cursos que son de rigor antes de ingresar como médico en el ejército. Concluidos allí mis estudios, fui puntualmente destinado el 5.0 de Fusileros de Northumberland en calidad de médico ayudante. El regimiento se hallaba por entonces estacionado en la India, y antes de que pudiera unirme a él, estalló la segunda guerra de Afganistán. Al desembarcar en Bombay me llegó la noticia de que las tropas a las que estaba agregado habían traspuesto la línea montañosa, muy dentro ya de territorio enemigo. Seguí, sin embargo, camino con muchos otros oficiales en parecida situación a la mía, hasta Candahar, donde sano y salvo, y en compañía por fin del regimiento, me incorporé sin más dilación a mi nuevo servicio."),
                                        MyMessage("Hola Mundo", "Es mi primera app con Kotlin y Jetpack Sin embargo, mientras los caballos subían por la áspera cuesta que desde el río conduce al castillo varios hombres del pueblo se acercaron al último caballo, que llevaba pendientes del arzón de la silla diversas aves cogidas del pico.\n" +
                                                "\n"),
                                        MyMessage("Hola Mundo", "Es mi primera app con Kotlin y Jetpack De los recuerdos de esta triste historia y de diversos datos incompletos, se ha formado el fondo de esta novela; pero ha debido aprovecharse la oportunidad para dar una especie de paseo por en medio de una sociedad que ha desaparecido en parte, haciendo de ella, "),
                                        MyMessage("Hola Mundo", "Es mi primera app con Kotlin y Jetpack aréceme, pues, que Teseo hace juego con Rómulo por muchas notas de semejanza: por ser uno y otro, de origen ilegítimo y oscuro, hubo fama de que eran hijos de dioses;\n" +
                                                "\n"),
                                        MyMessage("Hola Mundo", "Es mi primera app con Kotlin y Jetpack aréceme, pues, que Teseo hace juego con Rómulo por muchas notas de semejanza: por ser uno y otro, de origen ilegítimo y oscuro, hubo fama de que eran hijos de dioses;\n" +
                                                "\n"),
                                        MyMessage("Hola Mundo", "Es mi primera app con Kotlin y Jetpack"),
                                        MyMessage("Hola Mundo", "Es mi primera app con Kotlin y Jetpack"),
                                        MyMessage("Hola Mundo", "Es mi primera app con Kotlin y Jetpack"),
                                        )
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { //Bloque que indica que todo lo que está adentro son de interface Compose
            CJMTheme { //Todo el diseño de compose esta aqui dentro
                // A surface container using the 'background' color from the theme
                Surface(
                   // modifier = Modifier.fillMaxSize(),
                   // color = MaterialTheme.colors.background
                ) {
                    MyMessages(messages = messages)
                }
            }
        }
    }
}
@Composable
fun MyComponent(message: MyMessage){
    Row(modifier = Modifier
        .background(MaterialTheme.colors.background)
        .padding(8.dp)) { //Modificadores permiten cambiar la apariencia de distancias, colores, etc se ejecutan en orden,
            MyImage()
            MyTexts(message)
    }
}
@Composable
fun MyTexts(message: MyMessage){
    var expanded by remember{ mutableStateOf(false) }
    Column( modifier = Modifier
        .padding(start = 8.dp)
        .clickable {  //Bloque del listener para cada click
            //Remember nos permite guardar variables en una variable dentro del contexto de la pag.
            expanded = !expanded
        }) {
        MyText(message.title,
                MaterialTheme.colors.primary,
                MaterialTheme.typography.subtitle1,
            1
        )
        Spacer(modifier = Modifier.height(16.dp)) //Separaciones entre elementos
        MyText(message.body,
            MaterialTheme.colors.primary,
            MaterialTheme.typography.subtitle2,
            if(expanded) Int.MAX_VALUE else 1 )
    }

}




@Composable
fun MyText(name: String, color: Color, style: TextStyle, lines: Int = Int.MAX_VALUE) {
    //El valor line, define el numero de lineas que queremos pintar en nuestro card.
    Text(name, color = color, style = style, maxLines = lines)
}
data class MyMessage(val title: String, val body: String)

@Composable
fun MyMessages(messages: List<MyMessage>){
    /*LazyColumn: Se encarga de dibujar y renderizar elementos que si se están mostrando en la pantalla, de tal forma que se utilizará
        Menmos memoria
    */
    LazyColumn{
        items(messages){ message ->
            MyComponent(message = message)

        }
    }

}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES) //Cambiar a modo obscuro
@Preview(showSystemUi = true) // es para ver en la pantalla real.
@Composable
fun DefaultPreview() {
    CJMTheme {
        LazyColumn{
            items(messages){ message ->
                MyComponent(message = message)

            }
        }
    }
}
@Composable
fun MyImage(){
    Image(painterResource(R.drawable.ic_launcher_foreground), "Descripcion image",
        modifier = Modifier
            .clip(CircleShape)
            .background(Color.Gray)
            .size(64.dp)

    )
  //  Image(painter = , contentDescription = "imagen prueba")
}

