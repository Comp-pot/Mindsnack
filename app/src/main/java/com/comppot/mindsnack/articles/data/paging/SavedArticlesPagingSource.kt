package com.comppot.mindsnack.articles.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.comppot.mindsnack.articles.data.remote.SavingApi
import com.comppot.mindsnack.articles.data.remote.dto.toArticle
import com.comppot.mindsnack.articles.domain.model.Article
import com.comppot.mindsnack.core.data.utils.runSafe

class SavedArticlesPagingSource(val api: SavingApi) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val currentPage = params.key ?: 1
        val result = runSafe { api.getSavedArticles(currentPage) }
        return result.fold({ page ->
            LoadResult.Page(
                data = page.objects.map { it.toArticle() },
                prevKey = null,
                nextKey = if (page.hasNext) currentPage + 1 else null
            )
        }, { error -> LoadResult.Error(error) })
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int = 1
}
