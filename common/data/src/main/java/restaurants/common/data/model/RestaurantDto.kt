package restaurants.common.data.model

import com.google.gson.annotations.SerializedName
import restaurants.common.data.mapper.Mapper

data class RestaurantDto(
        var name: String,
        var city: String,

        @SerializedName(value = "image_url")
        var poster: String

) {
        fun toRestaurant(): Restaurant {
                return Restaurant(
                        name = name,
                        city = city,
                        poster = poster
                )
        }
}

fun List<RestaurantDto>.toPresentation(): MutableList<Restaurant> {
        return map { it.toRestaurant() }.toMutableList()
}

class RestaurantMapper: Mapper<RestaurantDto, Restaurant> {
        override fun map(input: RestaurantDto): Restaurant {
                return Restaurant(
                        name = input.name,
                        city = input.city,
                        poster = input.poster
                )
        }
}
