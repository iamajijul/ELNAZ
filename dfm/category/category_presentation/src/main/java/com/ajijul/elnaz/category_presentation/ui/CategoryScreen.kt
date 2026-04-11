package com.ajijul.elnaz.category_presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.ajijul.elnaz.category_presentation.viewmodel.CategoryEvent
import com.ajijul.elnaz.category_presentation.viewmodel.CategoryFormState
import com.ajijul.elnaz.category_presentation.viewmodel.CategoryListState
import com.ajijul.elnaz.category_presentation.viewmodel.CategoryViewModel
import com.ajijul.elnaz.category_presentation.viewmodel.CategoryViewModelFactory
import com.ajijul.elnaz.core.ui.components.AppFab
import com.ajijul.elnaz.core.ui.components.AppProgressOnScreen
import com.ajijul.elnaz.core.ui.components.AppText
import com.ajijul.elnaz.core.ui.components.AppTextEmptyScreen
import com.ajijul.elnaz.core.ui.components.AppTopBar
import com.ajijul.elnaz.di.entrypoints.CategoryDependenciesEntryPoint
import com.ajijul.elnaz.resources.R
import dagger.hilt.android.EntryPointAccessors

@Composable
fun CategoryScreen(navHostController: NavHostController) {
    AppText("CATEGORY SCREEN")
    val context = LocalContext.current

    // 1. Grab the entry point of category screen dependencies from the Application Context
    val categoryDependencies = remember(context) {
        EntryPointAccessors.fromApplication(
            context.applicationContext,
            CategoryDependenciesEntryPoint::class.java
        )
    }

    // 2. create category view model factory
    val categoryViewModelFactory = remember(categoryDependencies) {
        CategoryViewModelFactory(categoryDependencies)
    }
    //3. obtain view model from factory
    val categoryViewModel : CategoryViewModel = viewModel(factory = categoryViewModelFactory)


    val listState by categoryViewModel.listState.collectAsState()
    val formState by categoryViewModel.formState.collectAsState()

  /*  CategoryScreenContent(
        listState = listState,
        formState = formState,
        onEvent = categoryViewModel::onEvent
    )*/
}
/*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreenContent(
    listState: CategoryListState,
    formState: CategoryFormState,
    onEvent: (CategoryEvent) -> Unit
) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = stringResource(R.string.categories)
            )
        },
        floatingActionButton = {
            AppFab(
                icon = Icons.Default.Add,
                contentDescription = stringResource(R.string.add_new_category)
            ) {
                onEvent(CategoryEvent.OnAddNewCategoryClick)
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // --- Content States ---
            when {
                listState.isLoading -> {
                    AppProgressOnScreen()
                }
                listState.categories.isEmpty() -> {
                    AppTextEmptyScreen(text = stringResource(R.string.no_category_found))
                }
                else -> {
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(minSize = 160.dp),
                        contentPadding = PaddingValues(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(listState.categories, key = { it.id }) { category ->
                            CategoryItemCard(
                                category = category,
                                onEditClick = { onEvent(CategoryEvent.OnEditCategoryClick(category)) },
                                onDeleteClick = { onEvent(CategoryEvent.OnDeleteCategoryClick(category)) }
                            )
                        }
                    }
                }
            }
        }

        // --- Bottom Sheet Integration ---
        if (formState.isBottomSheetVisible) {
            CategoryFormBottomSheet(
                listState = listState,
                formState = formState,
                onEvent = onEvent
            )
        }
    }
}

// ============================================================================
// 3. THE GRID ITEM
// ============================================================================
@Composable
fun CategoryItemCard(
    category: com.ajijul.elnaz.domain.model.CategoryModel,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onEditClick() }, // Clicking the card also edits it
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            // Image Placeholder (Use Coil AsyncImage in production)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                */
/* AsyncImage(
                    model = category.categoryImage,
                    contentDescription = category.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                ) *//*

                Text("Image", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = category.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            if (category.description.isNotBlank()) {
                Text(
                    text = category.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Action Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = onEditClick, modifier = Modifier.size(32.dp)) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit", tint = MaterialTheme.colorScheme.primary)
                }
                IconButton(onClick = onDeleteClick, modifier = Modifier.size(32.dp)) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete", tint = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}

// ============================================================================
// 4. THE MODAL BOTTOM SHEET (The Form)
// ============================================================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryFormBottomSheet(
    listState: CategoryListState,
    formState: CategoryFormState,
    onEvent: (CategoryEvent) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = { onEvent(CategoryEvent.OnDismissBottomSheet) },
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .padding(bottom = 32.dp), // Extra padding for system nav bar
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = if (formState.editingCategoryId.isEmpty()) "Add New Category" else "Edit Category",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            // Name Field (With Error Handling)
            OutlinedTextField(
                value = formState.nameInput,
                onValueChange = { onEvent(CategoryEvent.OnNameChanged(it)) },
                label = { Text("Category Name *") },
                isError = formState.nameError != null,
                supportingText = {
                    if (formState.nameError != null) {
                        Text(formState.nameError.asString())
                    }
                },
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            // Description Field
            OutlinedTextField(
                value = formState.descriptionInput,
                onValueChange = { onEvent(CategoryEvent.OnDescriptionChanged(it)) },
                label = { Text("Description (Optional)") },
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                minLines = 2,
                maxLines = 4,
                modifier = Modifier.fillMaxWidth()
            )

            // Parent Category Dropdown
            ParentCategoryDropdown(listState = listState, formState = formState, onEvent = onEvent)

            // Save Button
            Button(
                onClick = { onEvent(CategoryEvent.OnSaveClick) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                enabled = !formState.isSaving
            ) {
                if (formState.isSaving) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = MaterialTheme.colorScheme.onPrimary,
                        strokeWidth = 2.dp
                    )
                } else {
                    Text("Save Category", fontSize = MaterialTheme.typography.titleMedium.fontSize)
                }
            }
        }
    }
}

// ============================================================================
// 5. THE ADJACENCY LIST DROPDOWN
// ============================================================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParentCategoryDropdown(
    listState: CategoryListState,
    formState: CategoryFormState,
    onEvent: (CategoryEvent) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    // Filter out the category we are currently editing to prevent infinite loops!
    val potentialParents = remember(listState.categories, formState.editingCategoryId) {
        listState.categories.filter { it.id != formState.editingCategoryId }
    }

    val selectedParentName = remember(formState.selectedParentId, potentialParents) {
        if (formState.selectedParentId == null) "None (Root Category)"
        else potentialParents.find { it.id == formState.selectedParentId }?.name ?: "Unknown"
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = selectedParentName,
            onValueChange = {},
            readOnly = true,
            label = { Text("Parent Category") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("None (Root Category)", fontWeight = FontWeight.Bold) },
                onClick = {
                    onEvent(CategoryEvent.OnParentSelected(null))
                    expanded = false
                }
            )
            HorizontalDivider()
            potentialParents.forEach { category ->
                DropdownMenuItem(
                    text = { Text(category.name) },
                    onClick = {
                        onEvent(CategoryEvent.OnParentSelected(category.id))
                        expanded = false
                    }
                )
            }
        }
    }
}*/
