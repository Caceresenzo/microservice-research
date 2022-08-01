<template>
  <p>processing...</p>
</template>

<script lang="ts" setup>
const { $auth } = useNuxtApp();

onBeforeMount(() => {
  $auth.loginWith("keycloak", {
    params: { prompt: "login", max_age: 1 },
  });
});
</script>
