package com.aliad.model.mapper

import com.aliad.model.BookItem
import com.aliad.model.MyBookItem
import com.aliad.model.MyCategory
import com.aliad.model.CategoryDto
import com.aliad.model.Comment
import com.aliad.model.CommentDto
import com.aliad.model.DashBord
import com.aliad.model.DashboardDto
import com.aliad.model.ForgotPasswordDto
import com.aliad.model.LikeStory
import com.aliad.model.MyLikeStory
import com.aliad.model.PopularSearch
import com.aliad.model.PopularSearchDto
import com.aliad.model.PrivacyPolicy
import com.aliad.model.PrivacyPolicyDto
import com.aliad.model.Subscription
import com.aliad.model.SubscriptionDto
import com.aliad.model.User
import com.aliad.model.LoginDto
import com.aliad.model.MyEarnHistory
import com.aliad.model.ResetPasswordResponse
import com.aliad.model.SearchStoryDto
import com.aliad.model.StoryCount
import com.aliad.model.StoryCountDto
import com.aliad.model.earn_history.EarnHistory
import com.aliad.model.earn_history.EarnHistoryDto
import com.aliad.model.subscription_history.Payment

object DataMapper {

    fun earningHistoryDtoToEarningHistory(earnHistoryDto: EarnHistoryDto) : List<MyEarnHistory>{
        val earningHistoryList  = ArrayList<MyEarnHistory>()
        earnHistoryDto.data.forEach { dataDto ->
           earningHistoryList.add(MyEarnHistory(
               amount = dataDto.amount?: "0",
               cardType = dataDto.card_type?: "",
               id = dataDto.id?: 0,
               storyNameBn = dataDto.story?.title_bn?: "",
               createAt = dataDto.created_at?: "",
               views = dataDto.views?: "0",
               userId = dataDto.user_id?: "0"
           ))
       }
        return earningHistoryList
    }

    fun storyCountDtoTOStoryCount(storyCountDto: StoryCountDto) : StoryCount {
        return StoryCount(
            liveStoryCount = storyCountDto.liveStory,
            pendingStoryCount = storyCountDto.pendingStory,
            joinSince = storyCountDto.join_since
        )
    }

    fun categoryDtoToMyCategory(categoryDto: CategoryDto) : MyCategory {
        return MyCategory(
            name = categoryDto.name,
            name_bn = categoryDto.name_bn,
            id = categoryDto.id,
            image = categoryDto.image
        )
    }

    fun toMyCategoryList(list: List<CategoryDto>) : List<MyCategory> {
        return list.map { categoryDto ->
            categoryDtoToMyCategory(categoryDto = categoryDto)
        }
    }

    
    fun resetPasswordDtoTOResetPasswordResponse(forgotPasswordDto: ForgotPasswordDto) : ResetPasswordResponse{
        return ResetPasswordResponse(
            messageEn = forgotPasswordDto.message_en,
            messageBn = forgotPasswordDto.message_bn,
            emailAddress = forgotPasswordDto.resetData.email,
            token = forgotPasswordDto.resetData.token
        )
    }

    fun toPayment(payment: Payment): com.aliad.model.Payment {
        return com.aliad.model.Payment(
            amount = payment.amount,
            cardType = payment.card_type,
            id = payment.id,
            planName = payment.plan_name,
            planNameBn = payment.plan_name_bn,
            transactionID = payment.transaction_id,
            userId = payment.user_id,
            userName = payment.user_name,
            expiryDate = payment.expiry_date,
            createdDate = payment.created_at
        )
    }

    fun toPopularSearch(popularSearchDto: PopularSearchDto): PopularSearch {
        return PopularSearch(storyList = popularSearchDto.popularSearchStories.map { toBookModel(it) })
    }

    fun toUser(loginDto: LoginDto, accessToken: String = ""): User {
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

    fun toCategory(categoryDto: CategoryDto): MyCategory {
        return MyCategory(
            name = categoryDto.name,
            name_bn = categoryDto.name_bn,
            id = categoryDto.id,
            image = categoryDto.image
        )
    }

    fun toCategoryList(categoryDtoList: List<CategoryDto>): List<MyCategory> {
        val categoryList = mutableListOf<MyCategory>()
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
            isPayAble = bookItem.is_payable,
            storyID = bookItem.id
        )
    }

    fun toMyLikeStory(likeStory: LikeStory) : MyLikeStory{
        return MyLikeStory(
            storyID = likeStory.id,
            category_name = likeStory.category_name,
            category_name_bn = likeStory.category_name_bn,
            titleBn = likeStory.title_bn,
            image = likeStory.image,
            rating = likeStory.rating,
            summaryBn = likeStory.summary_bn,
            isPayAble = likeStory.is_payable
        )
    }

    fun toStoryList(likeStories: List<LikeStory>): List<MyLikeStory> {
        val bookItemList = mutableListOf<MyLikeStory>()
        likeStories.forEach { likeStories ->
            val story = toMyLikeStory(likeStories)
            bookItemList.add(story)
        }
        return bookItemList
    }


    fun toBookFromSearchStoryDto(searchStoryDto: SearchStoryDto): MyBookItem {

        return MyBookItem(
            category_name = searchStoryDto.data[0].category_name,
            category_name_bn = searchStoryDto.data[0].category_name_bn,
            created_at = searchStoryDto.data[0].created_at,
            titleBn = searchStoryDto.data[0].title_bn,
            image = searchStoryDto.data[0].image,
            rating = searchStoryDto.data[0].rating,
            authorName = searchStoryDto.data[0].user_name,
            summaryBn = searchStoryDto.data[0].summary_bn,
            summaryEn = searchStoryDto.data[0].summary,
            isPayAble = searchStoryDto.data[0].is_payable,
            storyID = searchStoryDto.data[0].id,
            publishDate = searchStoryDto.data[0].publish_date,
            views = searchStoryDto.data[0].views,
            likeStories = toStoryList(searchStoryDto.data[0].likeStories),
            user = toUser(loginDto = searchStoryDto.data[0].user?: LoginDto())
        )
    }

    fun toDashBoardModel(dashboardDto: DashboardDto): DashBord {
        return DashBord(
            lisOfPopularStories = dashboardDto.mostPopularStories.map { toBookModel(it) },
            lifOfAllStories = dashboardDto.allStories.map { story -> toBookModel(story) },
            listOfNewReleaseStories = dashboardDto.newReleaseStories.map { story ->
                toBookModel(
                    story
                )
            }
        )
    }

    fun toSubscriptionPlan(subscriptionDto: SubscriptionDto): Subscription {
        return Subscription(
            days = subscriptionDto.days,
            id = subscriptionDto.id,
            name = subscriptionDto.name,
            name_bn = subscriptionDto.name_bn,
            price = subscriptionDto.price,
            status = subscriptionDto.status
        )
    }

    fun toSubscriptionPlanList(subscriptionDto: List<SubscriptionDto>): List<Subscription> {
        val subscriptionPlanList = mutableListOf<Subscription>()
        subscriptionDto.forEach { subscription ->
            subscriptionPlanList.add(toSubscriptionPlan(subscription))
        }
        return subscriptionPlanList
    }

    fun toPrivacyPolicy(privacyPolicyDto: PrivacyPolicyDto): PrivacyPolicy {
        return PrivacyPolicy(
            description = privacyPolicyDto.description ?: "",
            descriptionBn = privacyPolicyDto.description_bn ?: "",
            id = privacyPolicyDto.id ?: 0,
            title = privacyPolicyDto.title ?: "",
            titleBn = privacyPolicyDto.title_bn ?: ""
        )
    }

    fun commentDtoToComment(commentDto: CommentDto) : Comment{
        return Comment(
            comment = commentDto.comment?: "",
            id = commentDto.id?: 0,
            rating = commentDto.rating?: 0
        )
    }

}