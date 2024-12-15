package com.example.budgetplan.data

import com.example.budgetplan.domain.User

class UserMapper {

    val taskConverter = TaskConverter()

    fun mapEntityToDbModel(user: User): UserDbModel{
        return UserDbModel(
            id = user.id,
            name = user.name,
            money = user.money,
            monthProfit = user.monthProfit,
            monthLosses = user.monthLosses,
            lastTask = taskConverter.fromTask(user.lastTask)?: ""
        )
    }

    fun mapDbModelToEntity(userDbModel: UserDbModel?): User? {
        if (userDbModel != null){
            return User(
                id = userDbModel.id,
                name = userDbModel.name,
                money = userDbModel.money,
                monthProfit = userDbModel.monthProfit,
                monthLosses = userDbModel.monthLosses,
                lastTask = taskConverter.toTask(userDbModel.lastTask) ?: null
            )
        } else {
            return null
        }
    }


    fun mapListDbModelToListEntity(list: List<UserDbModel>): List<User> = list.map {
        mapDbModelToEntity(it)!!
    }
}