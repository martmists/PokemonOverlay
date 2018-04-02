import org.json.JSONObject
import java.io.File

val pokemonDB = JSONObject(File("out.json").readText())
val ballsDB = JSONObject(File("balls.json").readText())
val typesDB = JSONObject(File("types.json").readText())


class Pokemon(id: Any) {
    var level = 1
    var name = ""
    var nickname = ""
    var icon = ""
    var dexno = ""
    var types = mutableListOf<String>()
    var ball = ""
    var item = ""

    init {
        if (id != 0) {
            dexno = id.toString().padStart(3, '0')

            val json = pokemonDB.getJSONObject(dexno)

            dexno = json.getString("dexno")

            for (type in json.getJSONArray("types")) {
                types.add(typesDB.getString(type.toString()))
            }

            name = json.getString("name")
            nickname = name
            icon = json.getString("url")
        } else {
            icon = "http://via.placeholder.com/24x24/f4f4f4/f4f4f4"
            ball = "http://via.placeholder.com/24x24/f4f4f4/f4f4f4"
        }
    }

    fun inc(){
        level += 1
    }

    fun dec(){
        level -= 1
    }

    fun setPNick(name: String){
        nickname = name
    }

    fun setPLevel(level: Int){
        this.level = level
    }

    fun setPBall(ball: String){
        this.ball = ballsDB.getString(ball)
    }

    fun setPItem(name: String){
        this.item = name
    }
}


fun getAllpokemon(): Array<Pokemon>{
    val allPokemon = mutableListOf<Pokemon>()

    for (key in pokemonDB.keys()){
        allPokemon.add(Pokemon(key))
    }

    return allPokemon.toTypedArray()
}