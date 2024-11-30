package com.example.budgetplan.data

import com.example.budgetplan.domain.User

class TaskMapper {

    fun mapEntityToDbModel(user: User): UserDbModel{
        return UserDbModel(
            id = user.id,
            name = user.name,
            money = user.money,
            monthProfit = user.monthProfit,
            monthLosses = user.monthLosses,
            lastTask = user.lastTask
        )
    }

    fun mapDbModelToEntity(userDbModel: UserDbModel): User {
        return User(
            id = userDbModel.id,
            name = userDbModel.name,
            money = userDbModel.money,
            monthProfit = userDbModel.monthProfit,
            monthLosses = userDbModel.monthLosses,
            lastTask = userDbModel.lastTask
        )
    }


    fun mapListDbModelToListEntity(list: List<UserDbModel>): List<User> = list.map {
        mapDbModelToEntity(it)
    }
}