<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
[${ d.id }] <br />
<c:set var="d2" value="${ d }" />
[${ d2 }] <br />
[${ d2.id }] <br />

[${ c.id }] <br />
<c:set var="c2" value="${ c }" />
[${ c2 }] <br />
[${ c2.id }] <br />
</body>
</html>
