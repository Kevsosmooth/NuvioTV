package com.nuvio.tv.ui.screens.detail

import com.nuvio.tv.domain.model.ContentType
import com.nuvio.tv.domain.model.Meta
import com.nuvio.tv.domain.model.PosterShape
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class UpNextFullShowTest {

    @Test
    fun `simkl up next item resolves to its imdb id`() {
        assertEquals("tt11540908", resolveFullShowId(meta("simkl_upnext_tt11540908_S1E6", imdbId = "tt11540908")))
    }

    @Test
    fun `falls back to the id embedded in the up next id`() {
        assertEquals("tt0203259", resolveFullShowId(meta("mdblist_upnext_tt0203259_S28E1")))
        assertEquals("tt0903747", resolveFullShowId(meta("upnext_tt0903747_S03E07")))
    }

    @Test
    fun `regular show ids get no full show link`() {
        assertNull(resolveFullShowId(meta("tt11540908", imdbId = "tt11540908")))
        assertNull(resolveFullShowId(meta("tmdb:1396", imdbId = "tt0903747")))
    }

    @Test
    fun `up next id without a usable imdb id gets no link`() {
        assertNull(resolveFullShowId(meta("simkl_upnext_tmdb:1396_S1E2")))
    }

    private fun meta(id: String, imdbId: String? = null) = Meta(
        id = id,
        type = ContentType.SERIES,
        name = "Show",
        poster = null,
        posterShape = PosterShape.POSTER,
        background = null,
        logo = null,
        description = null,
        releaseInfo = null,
        imdbRating = null,
        genres = emptyList(),
        runtime = null,
        director = emptyList(),
        cast = emptyList(),
        videos = emptyList(),
        country = null,
        awards = null,
        language = null,
        links = emptyList(),
        imdbId = imdbId
    )
}
