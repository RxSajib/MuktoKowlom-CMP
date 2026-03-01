package com.aliad.model.mapper

import com.aliad.model.BookItem
import com.aliad.model.MyBookItem
import com.aliad.model.Category
import com.aliad.model.CategoryDto
import com.aliad.model.CategoryWiseBookDto
import com.aliad.model.DashBord
import com.aliad.model.DashboardDto
import com.aliad.model.PrivacyPolicy
import com.aliad.model.PrivacyPolicyDto
import com.aliad.model.Subscription
import com.aliad.model.SubscriptionDto
import com.aliad.model.User
import com.aliad.model.login.LoginDto

object DataMapper {

    fun toUser(loginDto: LoginDto, accessToken : String) : User{
        return User(
            name = loginDto.name,
            id = loginDto.id,
            email = loginDto.email,
            isVerified = loginDto.is_verified,
            phone = loginDto.phone,
            phoneTwo = loginDto.phone_two,
            address = loginDto.address,
            profileImage = loginDto.completedProfileImage,
            accessToken = accessToken,
            createAtDate = loginDto.created_at
        )
    }

    fun toCategory(categoryDto: CategoryDto): Category {
        return Category(
            name = categoryDto.name,
            name_bn = categoryDto.name_bn,
            id = categoryDto.id,
            image = categoryDto.image
        )
    }

    fun toCategoryList(categoryDtoList: List<CategoryDto>): List<Category> {
        val categoryList = mutableListOf<Category>()
        categoryDtoList.forEach {
            categoryList.add(toCategory(it))
        }
        return categoryList
    }


    fun toBookModel(bookItem: BookItem): MyBookItem {
        return MyBookItem(
            category_name = bookItem.category_name,
            category_name_bn = bookItem.category_name_bn,
            created_at = bookItem.created_at,
            titleBn = bookItem.title_bn,
            image = bookItem.image,
            rating = bookItem.rating,
            authorName = bookItem.user_name,
            summaryBn = bookItem.summary_bn,
            summaryEn = bookItem.story_bn,
            isPayAble = bookItem.is_payable
        )
    }

    fun toDashBoardModel(dashboardDto: DashboardDto) : DashBord{
        return DashBord(
            lisOfPopularStories = dashboardDto.mostPopularStories.map { toBookModel(it) },
            lifOfAllStories = dashboardDto.allStories.map { story -> toBookModel(story) },
            listOfNewReleaseStories = dashboardDto.newReleaseStories.map { story -> toBookModel(story) }
        )
    }

    fun toSubscriptionPlan(subscriptionDto: SubscriptionDto) : Subscription{
        return Subscription(
            days = subscriptionDto.days,
            id = subscriptionDto.id,
            name = subscriptionDto.name,
            name_bn = subscriptionDto.name_bn,
            price = subscriptionDto.price,
            status = subscriptionDto.status
        )
    }
    fun toSubscriptionPlanList(subscriptionDto: List<SubscriptionDto>) : List<Subscription>{
        val subscriptionPlanList = mutableListOf<Subscription>()
        subscriptionDto.forEach {subscription ->
            subscriptionPlanList.add(toSubscriptionPlan(subscription))
        }
        return subscriptionPlanList
    }

    fun toPrivacyPolicy(privacyPolicyDto: PrivacyPolicyDto) : PrivacyPolicy {
        return PrivacyPolicy(
            description = privacyPolicyDto.description?: "",
            descriptionBn = privacyPolicyDto.description_bn?: "",
            id = privacyPolicyDto.id?: 0,
            title = privacyPolicyDto.title?: "",
            titleBn = privacyPolicyDto.title_bn?: ""
        )
    }
}