<template>
  <p>processing...</p>
</template>

<script lang="ts" setup>
const { $auth, $router } = useNuxtApp();

onBeforeMount(() => {
  if ($auth.loggedIn) {
    $auth.logout();
  } else {
    $router.push("/")
  }
});

</script>
