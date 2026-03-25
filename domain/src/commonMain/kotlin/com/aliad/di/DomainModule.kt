package com.aliad.di

import com.aliad.usecase.CategoryUseCase
import com.aliad.usecase.CategoryWiseBookUseCase
import com.aliad.usecase.DashBoardUseCase
import com.aliad.usecase.LoginUseCase
import com.aliad.usecase.PopularSearchUseCase
import com.aliad.usecase.PremiumPlanUseCase
import com.aliad.usecase.PrivacyPolicyUseCase
import com.aliad.usecase.SignUpUseCase
import com.aliad.usecase.StoryTypeUseCase
import com.aliad.usecase.SubscriptionHistoryUseCase
import com.aliad.usecase.dataStore.DeleteBoolDataUseCase
import com.aliad.usecase.dataStore.DeleteIntDataUseCase
import com.aliad.usecase.dataStore.GetBoolData
import com.aliad.usecase.dataStore.GetIntData
import com.aliad.usecase.dataStore.GetStringData
import com.aliad.usecase.dataStore.DeleteStringDataUseCase
import com.aliad.usecase.dataStore.SaveBoolData
import com.aliad.usecase.dataStore.SaveIntData
import com.aliad.usecase.dataStore.SaveStringData
import org.koin.dsl.module

val domainModule = module {
    factory {
        LoginUseCase(
            accountRepository = get()
        )
    }

    factory {
        CategoryUseCase(
            categoryRepository = get()
        )
    }

    factory {
        CategoryWiseBookUseCase(
           categoryRepository = get()
        )
    }

    factory {
        DashBoardUseCase(dashBordRepository = get())
    }

    factory {
        StoryTypeUseCase(storyType = get())
    }

    //datastore instace cereate use csase
    factory {
        SaveStringData(dataStoreRepository = get())
    }
    factory {
        GetStringData(dataStoreRepository = get())
    }
    factory {
        SaveIntData(dataStoreRepository = get())
    }
    factory {
        GetIntData(dataStoreRepository = get())
    }
    factory {
        SaveBoolData(dataStoreRepository = get())
    }
    factory {
        GetBoolData(dataStoreRepository = get())
    }
    factory {
        DeleteStringDataUseCase(dataStoreRepository = get())
    }
    factory {
        DeleteIntDataUseCase(dataStoreRepository = get())
    }
    factory {
        DeleteBoolDataUseCase(dataStoreRepository = get())
    }
    //datastore instace cereate use csase
    
    
    factory {
        PremiumPlanUseCase(
            profileRepository = get()
        )
    }

    factory {
        PrivacyPolicyUseCase(
            profileRepository = get()
        )
    }

    factory {
        PopularSearchUseCase(
            searchRepository = get()
        )
    }

    factory {
        SubscriptionHistoryUseCase(subscriptionHistoryRepository = get())
    }

    factory {
        SignUpUseCase(accountRepository = get())
    }
}