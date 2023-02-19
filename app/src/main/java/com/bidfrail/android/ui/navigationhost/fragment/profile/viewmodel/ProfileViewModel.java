package com.bidfrail.android.ui.navigationhost.fragment.profile.viewmodel;

import androidx.lifecycle.ViewModel;
import com.bidfrail.android.data.repository.LocalRepository;
import com.bidfrail.android.data.repository.RemoteRepository;
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ProfileViewModel extends ViewModel {

    private LocalRepository localRepository;
    private RemoteRepository remoteRepository;

    @Inject
    public ProfileViewModel(LocalRepository localRepository, RemoteRepository remoteRepository) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
    }
}
